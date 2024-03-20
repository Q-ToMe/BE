package com.example.qtome_be.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class QuestionRequest {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public class create {
        private String bodyText;
        private QuestionType questionType;
    }

}
