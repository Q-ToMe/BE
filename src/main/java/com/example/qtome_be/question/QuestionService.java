package com.example.qtome_be.question;

import com.example.qtome_be.member.Member;
import com.example.qtome_be.member.MemberAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionAdaptor questionAdaptor;
    @Autowired
    private MemberAdaptor memberAdaptor;

    public void createQuestion(String email, QuestionRequest.create createRequest) {
        Member member = memberAdaptor.memberFind(email);
        Question question = Question.builder().bodyText(createRequest.getBodyText()).questionType(createRequest.getQuestionType()).member(member).build();
        questionAdaptor.save(question);
    }

    public List<QuestionResponse.Find> findAllQuestions(String email) {
        Member member = memberAdaptor.memberFind(email);
        List<Question> questionList = questionAdaptor.findAllQuestions(member);
        return QuestionResponse.Find.toReponses(questionList);
    }


    public QuestionResponse.Detail findDetailQuestion(Long id) {
        Question question = questionAdaptor.findQuestion(id);
        return QuestionResponse.Detail.toReponse(question);
    }
}
