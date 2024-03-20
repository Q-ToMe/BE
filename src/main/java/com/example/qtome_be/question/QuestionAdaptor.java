package com.example.qtome_be.question;

import com.example.qtome_be.config.Adaptor;
import org.springframework.beans.factory.annotation.Autowired;

@Adaptor
public class QuestionAdaptor {
    @Autowired
    private QuestionRepository questionRepository;

    public void save(Question question) {
        questionRepository.save(question);
    }
}
