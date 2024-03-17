package com.example.qtome_be.follow;

import com.example.qtome_be.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class FollowResponse {
    @Schema(description = "발신자 이메일", example = "example@naver.com", required = true)

    private String sender;

    @Schema(description = "신청 상태", example = "수락: ACCEPTED," +
            "   거절: REJECTED," +
            "   초기상태: INITIAL,", required = true)
    private FollowStatus followStatus;

    public static List<FollowResponse> toReponses(List<Follow> follows){
        return follows.stream().map(follow ->
             FollowResponse.builder().sender(follow.getSender().getEmail()).followStatus(follow.getFollowStatus()).build()
            ).collect(Collectors.toList());
    }
}
