package com.example.qtome_be.multipleChoice;

import com.example.qtome_be.question.Question;
import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class MultipleChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Question question;

    //TODO 질문+정답 묶어서 복합유니크 가능한지?
    private Boolean isAnswer;
    private String bodyText;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

}
