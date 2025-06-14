package com.karthik.QuizzBackend.repo;

import com.karthik.QuizzBackend.Model.Quizz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizRepo extends JpaRepository<Quizz,Long> {

    Optional<Quizz> findByTitle(String title);
}
