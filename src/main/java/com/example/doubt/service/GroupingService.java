package com.example.doubt.service;

import com.example.doubt.model.GroupEntity;
import com.example.doubt.model.Post;
import com.example.doubt.repo.GroupRepository;
import com.example.doubt.repo.PostRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Naive grouping service — scheduled.
 * It:
 *  - polls unprocessed posts
 *  - groups by top keywords (simple heuristic)
 *  - creates a GroupEntity and assigns posts to it
 */
@Service
public class GroupingService {

    private final PostRepository postRepository;
    private final GroupRepository groupRepository;

    public GroupingService(PostRepository postRepository, GroupRepository groupRepository) {
        this.postRepository = postRepository;
        this.groupRepository = groupRepository;
    }

    @Scheduled(fixedDelay = 3000)
    public void process() {
        List<Post> unprocessed = postRepository.findByProcessedFalseOrderByCreatedAtAsc();
        if (unprocessed.isEmpty()) return;

        // build simple clusters by top two keywords
        Map<String, List<Post>> map = new HashMap<>();
        for (Post p : unprocessed) {
            List<String> kws = extractKeywords(p.getContent());
            String key = String.join("|", kws.stream().limit(2).collect(Collectors.toList()));
            if (key.isBlank()) key = p.getContent().substring(0, Math.min(20, p.getContent().length()));
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(p);
        }

        for (Map.Entry<String, List<Post>> e : map.entrySet()) {
            List<Post> cluster = e.getValue();
            if (cluster.isEmpty()) continue;
            String title = cluster.get(0).getContent();
            List<String> keywords = extractKeywords(title);
            GroupEntity g = new GroupEntity();
            g.setTitle(title.length() > 120 ? title.substring(0, 120) : title);
            g.setKeywordsCsv(String.join(",", keywords));
            g.setCount(cluster.size());
            g.setLastUpdated(java.time.OffsetDateTime.now());
            g = groupRepository.save(g);

            // assign posts to group and mark processed
            for (Post p : cluster) {
                p.setGroupId(g.getId());
                p.setProcessed(true);
                postRepository.save(p);
            }
        }
    }

    private static List<String> extractKeywords(String text){
        if (text == null) return Collections.emptyList();
        String lower = text.toLowerCase();
        String[] tokens = lower.split("\\W+");
        Set<String> stop = new HashSet<>(Arrays.asList("the","is","in","and","a","to","of","how","i","my","on","for","with","can","do","what","please","help"));
        Map<String,Integer> freq = new HashMap<>();
        for(String t : tokens){
            if (t.length() < 3) continue;
            if (stop.contains(t)) continue;
            freq.put(t, freq.getOrDefault(t,0)+1);
        }
        return freq.entrySet().stream().sorted((a,b)->b.getValue()-a.getValue()).limit(4).map(Map.Entry::getKey).collect(Collectors.toList());
    }
}
