package com.example.qtome_be.multipleChoice;

import com.example.qtome_be.config.Adaptor;
import com.example.qtome_be.question.Question;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Adaptor
public class MultipleChoiceAdaptor {
    @Autowired
    private MultipleChoiceRepository multipleChoiceRepository;

    public void saveAll(List<MultipleChoice> multipleChoices) {
        multipleChoiceRepository.saveAll(multipleChoices);
    }

    public List<MultipleChoice> findMultipleChoices(Question question) {
        return multipleChoiceRepository.findAllByQuestion(question);
    }
}
