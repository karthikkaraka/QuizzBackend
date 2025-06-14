package com.karthik.QuizzBackend.controller;

import com.karthik.QuizzBackend.Dto.QuestionsResponce;
import com.karthik.QuizzBackend.Dto.QuizResponce;
import com.karthik.QuizzBackend.Dto.QuizSubmissionRequest;
import com.karthik.QuizzBackend.Dto.QuizSubmissionResponce;
import com.karthik.QuizzBackend.Model.Question;
import com.karthik.QuizzBackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Quizz/user")
public class UserController {
    @Autowired
    private UserService userservice;


    @GetMapping("/getallquizes")
    public List<QuizResponce> getallquizes()
    {
        return userservice.getallquizes();
    }
    @GetMapping("/attemptquiz/{quizid}")
    public ResponseEntity<List<QuestionsResponce>> getallquestions(@PathVariable Long quizid)
    {
        return userservice.getallquestions(quizid);
    }
    @PostMapping("/submitquiz")
    public ResponseEntity<QuizSubmissionResponce> submitquiz(@RequestBody QuizSubmissionRequest request)
    {
       return userservice.submitquiz(request);
    }
}
