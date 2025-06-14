package com.karthik.QuizzBackend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionsResponce {
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
