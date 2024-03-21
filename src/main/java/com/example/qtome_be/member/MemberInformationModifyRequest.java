package com.example.qtome_be.member;

import lombok.*;

@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Setter
public class MemberInformationModifyRequest {

//    @Schema(description = "닉네임", example = "예동이", required = false)
    private String nickname;

//    @Schema(description = "프로필 사진", example = "thumbnail.jpg", required = false)
    private String thumbnail;
}
