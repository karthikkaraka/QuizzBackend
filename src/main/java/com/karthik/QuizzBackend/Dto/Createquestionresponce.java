package com.karthik.QuizzBackend.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Createquestionresponce {
    private Long questionid;
    private String question;
}
