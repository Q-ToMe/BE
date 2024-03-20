package com.example.qtome_be.multipleChoice;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

}
