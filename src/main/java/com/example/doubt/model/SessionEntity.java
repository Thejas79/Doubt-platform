package com.example.doubt.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "sessions")
public class SessionEntity {
    @Id
    private String id; // UUID string

    private String anonTag;

    private OffsetDateTime createdAt = OffsetDateTime.now();

    public SessionEntity() {}

    public SessionEntity(String id, String anonTag) {
        this.id = id;
        this.anonTag = anonTag;
        this.createdAt = OffsetDateTime.now();
    }

    // getters / setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getAnonTag() { return anonTag; }
    public void setAnonTag(String anonTag) { this.anonTag = anonTag; }
    public OffsetDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(OffsetDateTime createdAt) { this.createdAt = createdAt; }
}
