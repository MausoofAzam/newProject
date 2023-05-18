package com.snort.practice.Repository;

import com.snort.practice.entity.UserQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQuestionRepository extends JpaRepository<UserQuestion,Long> {
    List<UserQuestion> findByUserId(Long userId);
}
