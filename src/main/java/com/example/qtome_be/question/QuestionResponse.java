package com.example.qtome_be.question;

import com.example.qtome_be.member.Member;
import com.example.qtome_be.member.MemberResponse;
import com.example.qtome_be.multipleChoice.MultipleChoice;
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
    public static class Find {
        private Long id;

        private LocalDateTime createdAt;

        private MemberResponse member;

        private String bodyText;

        private QuestionType questionType;

        private List<MultipleChoiceResponse> multipleChoiceList;
        public static Find toReponse(Question question,List<MultipleChoice> multipleChoiceList){
            return Find.builder().id(question.getId()).createdAt(question.getCreatedAt())
                    .member(MemberResponse.builder().nickname(question.getMember().getNickname()).email(question.getMember().getEmail()).build())
                    .bodyText(question.getBodyText()).questionType(question.getQuestionType())
                    .multipleChoiceList(MultipleChoiceResponse.toReponse(multipleChoiceList))
                    .build();
        }

    }
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Detail {
        private Long id;

        private LocalDateTime createdAt;

        private MemberResponse member;

        private String bodyText;

        private QuestionType questionType;
        private List<MultipleChoiceResponse> multipleChoiceList;

        public static Detail toReponse(Question question, List<MultipleChoice> multipleChoiceList) {
            return Detail.builder().id(question.getId()).createdAt(question.getCreatedAt())
                    .member(MemberResponse.builder().nickname(question.getMember().getNickname()).email(question.getMember().getEmail()).build())
                    .bodyText(question.getBodyText()).questionType(question.getQuestionType())
                    .multipleChoiceList(MultipleChoiceResponse.toReponse(multipleChoiceList))
                    .build();
        }
    }
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class MultipleChoiceResponse {

        private Long id;
        private Boolean isAnswer;
        private String bodyText;
        private LocalDateTime createdAt;
        public static List<MultipleChoiceResponse> toReponse(List<MultipleChoice> multipleChoiceList){
            return multipleChoiceList.stream().map(multipleChoice ->
                    MultipleChoiceResponse.builder().id(multipleChoice.getId()).isAnswer(multipleChoice.getIsAnswer()).bodyText(multipleChoice.getBodyText()).createdAt(multipleChoice.getCreatedAt()).build()
            ).collect(Collectors.toList());        }
    }
}
