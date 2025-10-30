package com.example.doubt.repo;

import com.example.doubt.model.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
}
