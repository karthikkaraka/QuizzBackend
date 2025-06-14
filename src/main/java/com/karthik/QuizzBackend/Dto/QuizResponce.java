package com.karthik.QuizzBackend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponce {
    private Long id;
    private String title;
    private String description;
}
