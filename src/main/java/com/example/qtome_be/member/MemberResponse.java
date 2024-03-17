package com.example.qtome_be.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class MemberResponse {

    @Schema(description = "사용자 이메일", example = "example@naver.com", required = true)
    private String email;
    public static List<MemberResponse> toReponses(List<Member> members){
        return members.stream().map(member ->
                MemberResponse.builder().email(member.getEmail()).build()
        ).collect(Collectors.toList());
    }

}
