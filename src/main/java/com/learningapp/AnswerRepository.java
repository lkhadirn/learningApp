package com.learningapp;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer, Long> {
    @Query(value = "SELECT * FROM answer WHERE question_id = :questionId", nativeQuery = true)
    List<Answer> findByQuestionId(@Param("questionId") Long questionId);
}
