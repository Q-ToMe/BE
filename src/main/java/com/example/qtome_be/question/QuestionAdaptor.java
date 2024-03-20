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
}
