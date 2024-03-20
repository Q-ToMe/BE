package com.example.qtome_be.question;

import com.example.qtome_be.config.Adaptor;
import com.example.qtome_be.member.Member;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Adaptor
public class QuestionAdaptor {
    @Autowired
    private QuestionRepository questionRepository;

    public void save(Question question) {
        questionRepository.save(question);
    }

    public List<Question> findAllQuestions(Member member) {
        return questionRepository.findAllByMember(member);
    }

    public Question findQuestion(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("질문이 없습니다."));//TODO 예외처리

    }
}
