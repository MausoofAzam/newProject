package com.snort.practice.Repository;


import com.snort.practice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByCategoryAndLevel(String category, String level);

    List<Question> findBySetNumber(Integer setNumber);
    Integer countByCategoryAndLevelAndSetNumber(String category,String level,Integer setNumber);
    @Query(value = "select sum(total_marks) from question where category=:category and level= :level and set_number=:setNumber",nativeQuery = true)
    Integer sumByCategoryAndLevelAndSetNumber(String category,String level,Integer setNumber);
}
