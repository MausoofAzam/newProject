package com.snort.practice.Repository;

import com.snort.practice.entity.UserQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuestionRepository extends JpaRepository<UserQuestion,Long> {
}
