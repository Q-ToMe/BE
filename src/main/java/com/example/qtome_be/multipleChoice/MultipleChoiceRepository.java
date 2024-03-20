package com.example.qtome_be.multipleChoice;

import com.example.qtome_be.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MultipleChoiceRepository extends JpaRepository<MultipleChoice, Long> {
    List<MultipleChoice> findAllByQuestion(Question question);
}
