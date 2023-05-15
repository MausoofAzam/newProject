package com.snort.practice.Repository;

import com.snort.practice.entity.QuestionAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAssignmentRepository extends JpaRepository<QuestionAssignment,Long> {
}
