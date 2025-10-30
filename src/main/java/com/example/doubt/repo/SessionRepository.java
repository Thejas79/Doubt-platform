package com.example.doubt.repo;

import com.example.doubt.model.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<SessionEntity, String> {
}
