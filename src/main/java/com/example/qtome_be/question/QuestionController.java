package com.example.qtome_be.question;

import com.example.qtome_be.config.TokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public ResponseEntity createQuestion(HttpServletRequest request, @RequestBody QuestionRequest.create createRequest) {

        String email = tokenExtractor.extractToken(request);
        questionService.createQuestion(email, createRequest);
        return ResponseEntity.ok().build();
    }
}
