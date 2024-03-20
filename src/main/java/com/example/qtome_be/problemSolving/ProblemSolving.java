package com.example.qtome_be.problemSolving;

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
public class ProblemSolving {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
