package com.example.doubt.controller;

import com.example.doubt.model.Post;
import com.example.doubt.model.SessionEntity;
import com.example.doubt.repo.PostRepository;
import com.example.doubt.repo.SessionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostRepository postRepository;
    private final SessionRepository sessionRepository;

    public PostController(PostRepository postRepository, SessionRepository sessionRepository) {
        this.postRepository = postRepository;
        this.sessionRepository = sessionRepository;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() { return ResponseEntity.ok("ok"); }

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody CreatePost req) {
        // sessionId required (student gets it earlier)
        if (req.sessionId == null || req.content == null) return ResponseEntity.badRequest().body("missing");
        Optional<SessionEntity> s = sessionRepository.findById(req.sessionId);
        if (s.isEmpty()) return ResponseEntity.status(400).body("invalid session");
        Post p = new Post();
        p.setSessionId(req.sessionId);
        p.setAnonTag(s.get().getAnonTag());
        p.setContent(req.content);
        postRepository.save(p);
        return ResponseEntity.ok().body(new CreatedResp(p.getId()));
    }

    @GetMapping("/posts")
    public List<Post> listRecentPosts() {
        return postRepository.findTop200ByOrderByCreatedAtDesc();
    }

    public static record CreatePost(String sessionId, String content) {}
    public static record CreatedResp(Long id) {}
}
