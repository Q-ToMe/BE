package com.example.qtome_be.multipleChoice;

import com.example.qtome_be.config.Adaptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Adaptor
public class MultipleChoiceAdaptor {
    @Autowired
    private MultipleChoiceRepository multipleChoiceRepository;

    public void saveAll(List<MultipleChoice> multipleChoices) {
        multipleChoiceRepository.saveAll(multipleChoices);
    }
}
