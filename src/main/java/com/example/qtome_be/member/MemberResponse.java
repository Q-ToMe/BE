package com.example.qtome_be.member;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class MemberResponse {

//    @Schema(description = "사용자 닉네임", example = "예동이", required = true)
    private String nickname;

//    @Schema(description = "사용자 이메일", example = "example@naver.com", required = true)
    private String email;
    public static List<MemberResponse> toReponses(List<Member> members){
        return members.stream().map(member ->
                MemberResponse.builder().nickname(member.getNickname()).email(member.getEmail()).build()
        ).collect(Collectors.toList());
    }

}
