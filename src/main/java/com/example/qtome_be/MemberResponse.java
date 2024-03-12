package com.example.qtome_be;

import com.example.qtome_be.member.Member;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class MemberResponse {
    private Long id;

    private String email;
    private String nickname;

    private String thumbnail;

    public static List<MemberResponse> toReponses(List<Member> members){
        return members.stream().map(member ->
                MemberResponse.builder().id(member.getId()).email(member.getEmail()).nickname(member.getNickname()).thumbnail(member.getThumbnail()).build()
        ).collect(Collectors.toList());
    }

}
