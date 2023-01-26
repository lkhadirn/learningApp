package com.example.testtemplate;

import jakarta.persistence.Cacheable;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends CrudRepository<Question, Long> {


    @Modifying
    @Transactional
    @Query("UPDATE Question q SET q.explanation = :explanation WHERE q.id = :id")
    void updateExplanation(@Param("id") Long id, @Param("explanation") String explanation);

    @Query(value = "SELECT external_question_id FROM question", nativeQuery = true)
    List<Integer> findAllQuestionIds();

    @Query(value = "SELECT * FROM question ORDER BY RANDOM() LIMIT 1", nativeQuery = true)
    Question findRandom1();

    @Query(value = "SELECT id,explanation FROM question", nativeQuery = true)
    List<QuestionExplanationProjection> getQuestionsOnlyIdAndExplanation();

    interface QuestionExplanationProjection {
        Long getId();

        String getExplanation();
    }
}
