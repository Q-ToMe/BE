package com.example.qtome_be.follow;

import com.example.qtome_be.member.Member;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class FollowResponse {
    private Member sender;

    private FollowStatus followStatus;

    public static List<FollowResponse> toReponses(List<Follow> follows){
        return follows.stream().map(follow ->
             FollowResponse.builder().sender(follow.getSender()).followStatus(follow.getFollowStatus()).build()
            ).collect(Collectors.toList());
    }
}
