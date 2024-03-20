package com.example.qtome_be.question;

import com.example.qtome_be.member.Member;
import com.example.qtome_be.member.MemberResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionResponse {
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public class Find {
        private Long id;

        private LocalDateTime createdAt;

        private MemberResponse member;

        private String bodyText;

        private QuestionType questionType;
        public static List<Find> toReponses(List<Question> questionList){
            return questionList.stream().map(question ->
                    Find.builder().id(question.getId()).createdAt(question.getCreatedAt()).member(MemberResponse.builder().nickname(question.getMember().getNickname()).email(question.getMember().getEmail()).build()).bodyText(question.getBodyText()).questionType(question.getQuestionType()).build()
            ).collect(Collectors.toList());
        }

    }
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public class Detail {
        private Long id;

        private LocalDateTime createdAt;

        private MemberResponse member;

        private String bodyText;

        private QuestionType questionType;
        public static Detail toReponse(Question question){
            return Detail.builder().id(question.getId()).createdAt(question.getCreatedAt()).member(MemberResponse.builder().nickname(question.getMember().getNickname()).email(question.getMember().getEmail()).build()).bodyText(question.getBodyText()).questionType(question.getQuestionType()).build();
        }
    }
}
