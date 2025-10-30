package com.example.doubt.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "posts")
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionId;
    private String anonTag;
    @Column(columnDefinition = "TEXT")
    private String content;
    private boolean processed = false;
    private Long groupId; // optional
    private OffsetDateTime createdAt = OffsetDateTime.now();

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public String getAnonTag() { return anonTag; }
    public void setAnonTag(String anonTag) { this.anonTag = anonTag; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public boolean isProcessed() { return processed; }
    public void setProcessed(boolean processed) { this.processed = processed; }
    public Long getGroupId() { return groupId; }
    public void setGroupId(Long groupId) { this.groupId = groupId; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
