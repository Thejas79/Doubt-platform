package com.example.doubt.controller;

import com.example.doubt.model.GroupEntity;
import com.example.doubt.model.Post;
import com.example.doubt.repo.GroupRepository;
import com.example.doubt.repo.PostRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupRepository groupRepository;
    private final PostRepository postRepository;

    public GroupController(GroupRepository groupRepository, PostRepository postRepository) {
        this.groupRepository = groupRepository;
        this.postRepository = postRepository;
    }

    @GetMapping
    public List<GroupDto> listGroups() {
        return groupRepository.findAll().stream().sorted((a,b)->b.getLastUpdated().compareTo(a.getLastUpdated()))
                .map(g -> new GroupDto(g.getId(), g.getTitle(), g.getKeywordsCsv()==null?new String[]{}:g.getKeywordsCsv().split(","), g.getCount()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/messages")
    public List<MessageDto> groupMessages(@PathVariable Long id) {
        List<Post> posts = postRepository.findByGroupId(id);
        return posts.stream().map(p -> new MessageDto(p.getId(), p.getAnonTag(), p.getContent(), p.getCreatedAt())).collect(Collectors.toList());
    }

    public record GroupDto(Long id, String title, String[] keywords, int count) {}
    public record MessageDto(Long id, String anonTag, String content, java.time.OffsetDateTime createdAt) {}
}
