package com.example.qtome_be.question;

import com.example.qtome_be.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    private Member member;

    private String bodyText;

    private QuestionType questionType;
}