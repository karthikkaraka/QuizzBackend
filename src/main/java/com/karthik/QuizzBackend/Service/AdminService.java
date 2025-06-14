package com.karthik.QuizzBackend.Service;

import com.karthik.QuizzBackend.Dto.CreateQuizResponce;
import com.karthik.QuizzBackend.Dto.Createquestionresponce;
import com.karthik.QuizzBackend.Dto.QuizResponce;
import com.karthik.QuizzBackend.Model.Question;
import com.karthik.QuizzBackend.Model.Quizz;
import com.karthik.QuizzBackend.repo.QuestionRepo;
import com.karthik.QuizzBackend.repo.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private QuizRepo quizrepo;
    @Autowired
    private QuestionRepo querepo;

    public ResponseEntity<CreateQuizResponce> saveQuiz(Quizz quiz) {
        quizrepo.save(quiz);
       return new ResponseEntity<>(new CreateQuizResponce(quiz.getId(),quiz.getTitle()),HttpStatus.CREATED);
    }

    public ResponseEntity<Createquestionresponce> saveQuestion(Question ques, Long id) {
       Optional<Quizz> quiz =  quizrepo.findById(id);
       List<Question> questions ;
       Quizz originalquiz = null;

       if(quiz.isPresent())
       {
           originalquiz = quiz.get();
           questions = originalquiz.getQuestions();
           questions.add(ques);
           querepo.save(ques);
           return new ResponseEntity<>(new Createquestionresponce(ques.getId(), ques.getQuestion()),HttpStatus.CREATED);
       }
       else{
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
    public List<QuizResponce> getallquizzes() {
        List<Quizz> quizs = quizrepo.findAll();
        return quizs.stream().map(q->
        {
            QuizResponce dto = new QuizResponce();
            dto.setId(q.getId());
            dto.setTitle(q.getTitle());
            dto.setDescription(q.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    public void updatequiz(Quizz quiz, Long id) {
        Optional<Quizz> quizz = quizrepo.findByTitle(quiz.getTitle());
        Long originalId = null;
        Quizz originalquiz = null;
        if(quizz.isPresent())
        {
            originalquiz = quizz.get();
            originalId = originalquiz.getId();
        }
        if(originalId==id)
        {
            originalquiz.setTitle(quiz.getTitle());
            originalquiz.setDescription(quiz.getDescription());
            quizrepo.save(originalquiz);
        }
    }

    public ResponseEntity<String> deletequiz(Long id) {
        Optional<Quizz> quiz = quizrepo.findById(id);
        Quizz originalquiz = null;
        if(quiz.isPresent())
        {
            quizrepo.deleteById(id);
            originalquiz = quiz.get();
            return new ResponseEntity<>("deleted the quiz named:"+originalquiz.getTitle(), HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<String> updateQuestion(Long quizid, Question ques, Long quesid) {
        Optional<Quizz> quiz = quizrepo.findById(quizid);
        Quizz originalquiz = null;
        Question originalques = null;
        System.out.println("level1");
        if(quiz.isPresent())
        {
            originalquiz= quiz.get();
             List<Question> questions = originalquiz.getQuestions();
            System.out.println("level2");
            for(Question q : questions)
            {
                if(q.getId().equals(quesid))
                {
                    System.out.println("level3");
                    originalques = q;
                }
            }
              originalques.setQuestion(ques.getQuestion());
              originalques.setOption1(ques.getOption1());
              originalques.setOption2(ques.getOption2());
              originalques.setOption3(ques.getOption3());
              originalques.setOption4(ques.getOption4());
              originalques.setCorrect(ques.getCorrect());
              querepo.save(originalques);
            System.out.println("level4");
            return new ResponseEntity<>("updated the question named--"+originalques.getQuestion()+"-- ..",HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteQuestion(Long quizid, int quesindex) {
        Optional<Quizz> quizz = quizrepo.findById(quizid);
        Quizz originalquiz = null;
        List<Question> questions = null;
        if(quizz.isPresent())
        {
            originalquiz = quizz.get();
            questions = originalquiz.getQuestions();
            String question = questions.get(quesindex-1).getQuestion();
            if(quesindex<=0||quesindex>questions.size())
            {
                return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
            }
            questions.remove(quesindex-1);
            originalquiz.setQuestions(questions);
            quizrepo.save(originalquiz);
            return new ResponseEntity<>("DELETED THE QUESTION NAMED--"+question+"--",HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>("",HttpStatus.NOT_FOUND);
        }
    }

    public List<Question> getallquestions(Long quizid) {
        System.out.println("karthik karaka");
        Optional<Quizz> quizz = quizrepo.findById(quizid);
        Quizz originalquiz = null;
        List<Question> questions = null;
        if(quizz.isPresent()) {
            originalquiz = quizz.get();
          questions = originalquiz.getQuestions();
        }
        return questions;
    }
}
