package com.example.doubt.model;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "groups")
public class GroupEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String keywordsCsv; // comma separated keywords
    private int count = 0;
    private OffsetDateTime lastUpdated = OffsetDateTime.now();

    public GroupEntity() {}

    // getters/setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getKeywordsCsv() { return keywordsCsv; }
    public void setKeywordsCsv(String keywordsCsv) { this.keywordsCsv = keywordsCsv; }
    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }
    public OffsetDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(OffsetDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
