package com.karthik.QuizzBackend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizSubmissionRequest {
    private Long quizid;
    private List<Answersdto> answers;
}
