package com.example.qtome_be.question;

import com.example.qtome_be.member.MemberResponse;
import com.example.qtome_be.multipleChoice.MultipleChoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;


public class QuestionRequest {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public class create {
        private String bodyText;
        private QuestionType questionType;

        private List<MultipleChoiceCreateRequest> multipleChoiceCreateRequests;

        public void validate() {
            long trueCount = multipleChoiceCreateRequests.stream()
                    .filter(MultipleChoiceCreateRequest::getIsAnswer)
                    .count();

            if (trueCount != 1) {
                throw new RuntimeException("정확히 하나의 정답만 있어야 합니다."); //TODO 예외처리
            }
        }
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public class MultipleChoiceCreateRequest {
        private Boolean isAnswer;
        private String bodyText;

        public static List<MultipleChoice> createMultipleChoiceList(List<MultipleChoiceCreateRequest> requestList,Question question) {
            return requestList.stream().map(request ->
                    MultipleChoice.builder().question(question).bodyText(request.bodyText).isAnswer(request.getIsAnswer()).build()
            ).collect(Collectors.toList());
        }

    }
}
