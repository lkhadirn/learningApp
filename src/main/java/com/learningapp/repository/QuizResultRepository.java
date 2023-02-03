package com.learningapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.learningapp.model.QuizResult;

public interface QuizResultRepository extends CrudRepository<QuizResult, Integer> {
}