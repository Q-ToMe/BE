package com.example.qtome_be.question;

import com.example.qtome_be.config.TokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @GetMapping
    public ResponseEntity findAllQuestions(HttpServletRequest request) {
        String email = tokenExtractor.extractToken(request);
        List<QuestionResponse.Find> response = questionService.findAllQuestions(email);

        return ResponseEntity.ok(response);
    }

}
