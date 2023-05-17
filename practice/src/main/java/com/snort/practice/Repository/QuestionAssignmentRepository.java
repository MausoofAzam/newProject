package com.snort.practice.Repository;

import com.snort.practice.entity.UserQuestionAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionAssignmentRepository extends JpaRepository<UserQuestionAssignment, Long> {
    List<UserQuestionAssignment> findByCategoryAndLevelAndSetNumber(String category, String level, Integer setNumber);
}
