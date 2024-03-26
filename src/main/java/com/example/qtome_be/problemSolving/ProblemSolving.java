package com.example.qtome_be.problemSolving;

import com.example.qtome_be.member.Member;
import com.example.qtome_be.multipleChoice.MultipleChoice;
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
public class ProblemSolving {//객관식에만 제한되어있나?
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private MultipleChoice multipleChoice;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Member member;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
