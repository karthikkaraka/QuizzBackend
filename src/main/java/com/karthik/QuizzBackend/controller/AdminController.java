package com.karthik.QuizzBackend.controller;

import com.karthik.QuizzBackend.Dto.CreateQuizResponce;
import com.karthik.QuizzBackend.Dto.Createquestionresponce;
import com.karthik.QuizzBackend.Dto.QuizResponce;
import com.karthik.QuizzBackend.Model.Question;
import com.karthik.QuizzBackend.Model.Quizz;
import com.karthik.QuizzBackend.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Quizz/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    @Autowired
    private AdminService admservice;


  @PostMapping("/createquiz")
    public ResponseEntity<CreateQuizResponce> createQuiz(Authentication auth, @RequestBody Quizz quiz)
  {
      return admservice.saveQuiz(quiz);

  }
  @PostMapping("/createquestion")
    public ResponseEntity<Createquestionresponce> createQuestion(@RequestBody Question ques)
  {
      System.out.println("level 1");
      Long id = ques.getQuizz().getId();
     return admservice.saveQuestion(ques,id);
  }
  @GetMapping("/getallquizes")
  public List<QuizResponce> getallQuizzes()
  {
      return admservice.getallquizzes();
  }
  @PutMapping("/updatequiz/{id}")
  public ResponseEntity<String> updateQuiz(@RequestBody Quizz quiz,@PathVariable Long id)
  {
     admservice.updatequiz(quiz,id);
     return new ResponseEntity<>("updated the quizz named :"+quiz.getTitle(),HttpStatus.ACCEPTED);
  }
  @DeleteMapping("/deletequiz/{id}")
    public ResponseEntity<String> deletequizz(@PathVariable Long id)
  {
      return admservice.deletequiz(id);
  }
  @PutMapping("/updateQuestion/{Quizid}/{quesid}")
    public ResponseEntity<String> updatequestion(@RequestBody Question ques,@PathVariable Long Quizid,@PathVariable Long quesid)
  {
       return admservice.updateQuestion(Quizid,ques,quesid);
  }
  @DeleteMapping("/deleteQuestion/{quizid}/{quesindex}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long quizid,@PathVariable int quesindex)
  {
      return admservice.deleteQuestion(quizid,quesindex);
  }

}
