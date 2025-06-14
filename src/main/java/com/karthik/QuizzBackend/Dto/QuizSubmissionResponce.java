package com.karthik.QuizzBackend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizSubmissionResponce {
    private int totalquestions;
    private int noofcorrect;
}
