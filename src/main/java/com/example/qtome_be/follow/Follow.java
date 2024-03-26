package com.example.qtome_be.follow;


import com.example.qtome_be.member.Member;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member sender;

    @ManyToOne
    private Member receiver;
    @Builder.Default
    private FollowStatus followStatus=FollowStatus.INITIAL;

}
