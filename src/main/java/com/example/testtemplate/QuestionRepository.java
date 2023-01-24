package com.example.testtemplate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    @Query(value = "SELECT question_id FROM question", nativeQuery = true)
    List<Integer> findAllQuestionIds();

    @Query(value = "SELECT * FROM question ORDER BY RANDOM() LIMIT 10", nativeQuery = true)
    List<Question> findRandom10();


    @Query(value = "SELECT * FROM question ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Question findRandom1();


}
