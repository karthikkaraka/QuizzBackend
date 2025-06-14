package com.karthik.QuizzBackend.Service;

import com.karthik.QuizzBackend.Dto.*;
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
public class UserService {
    @Autowired
    private QuizRepo quizrepo;
   @Autowired
   QuestionRepo quesrepo;


    public List<QuizResponce> getallquizes() {
        List<Quizz> quizes = quizrepo.findAll();
        return quizes.stream().map(q ->{
           QuizResponce dto = new QuizResponce();
            dto.setId(q.getId());
            dto.setTitle(q.getTitle());
            dto.setDescription(q.getDescription());
            return dto;
        }).collect(Collectors.toList());
    }

    public ResponseEntity<List<QuestionsResponce>> getallquestions(Long quizid) {
        Optional<Quizz> quiz = quizrepo.findById(quizid);
        Quizz originalquiz = null;
        if(quiz.isPresent())
        {
            originalquiz = quiz.get();
            List<Question> questions = originalquiz.getQuestions();
            return new ResponseEntity<>( questions.stream().map(q->{
                QuestionsResponce dto = new QuestionsResponce();
                dto.setQuestion(q.getQuestion());
                dto.setOption1(q.getOption1());
                dto.setOption2(q.getOption2());
                dto.setOption3(q.getOption3());
                dto.setOption4(q.getOption4());
                return dto;
            }).collect(Collectors.toList()), HttpStatus.FOUND);

        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<QuizSubmissionResponce> submitquiz(QuizSubmissionRequest request) {

        int correct = 0;
        for(Answersdto answer : request.getAnswers())
        {
            Question question = quesrepo.findById(answer.getQueid()).orElse(null);
            if(question!=null && question.getCorrect()==answer.getAnswer())
            {
                correct++;
            }
        }
        return new ResponseEntity<>(new QuizSubmissionResponce(request.getAnswers().size(),correct),HttpStatus.OK);
    }
}
