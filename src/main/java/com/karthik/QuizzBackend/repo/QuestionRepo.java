package com.karthik.QuizzBackend.repo;

import com.karthik.QuizzBackend.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question,Long> {

}
