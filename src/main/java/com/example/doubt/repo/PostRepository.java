package com.example.doubt.repo;

import com.example.doubt.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByProcessedFalseOrderByCreatedAtAsc();
    List<Post> findTop200ByOrderByCreatedAtDesc();
    List<Post> findByGroupId(Long groupId);
}
