package com.example.qtome_be.question;

import com.example.qtome_be.config.TokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private QuestionService questionService;

    /**
     * 이 클래스는 네트워킹 작업을 수행합니다.
     * 자세한 정보는 공식 웹사이트를 참고하세요.
     *
     * @see <a href="https://www.example.com">공식 웹사이트</a>
     */
    @PostMapping
    public ResponseEntity createQuestion(HttpServletRequest request, @RequestBody QuestionRequest.create createRequest) {
        String email = tokenExtractor.extractToken(request);
        createRequest.validate();
        questionService.createQuestion(email, createRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity findAllQuestions(HttpServletRequest request) {
        String email = tokenExtractor.extractToken(request);
        List<QuestionResponse.Find> response = questionService.findAllQuestions(email);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/detail")
    public ResponseEntity findQuestion(@RequestParam Long id) {
        QuestionResponse.Detail response = questionService.findDetailQuestion(id);
        return ResponseEntity.ok(response);
    }
}
