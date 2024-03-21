package com.example.qtome_be.friend;

import com.example.qtome_be.member.Member;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private Member master;

    @ManyToOne
    private Member slave;

}
