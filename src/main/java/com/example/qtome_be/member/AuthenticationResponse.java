package com.example.qtome_be.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AuthenticationResponse {
//    @Schema(description = "신규 가입자 여부", example = "true", required = true)
    private Boolean isNew;
//    @Schema(description = "jwt 토큰 (유효기간 10년)", example = "asdasdawa-awdadasawd-asdasdwadawd", required = true)
    private final String jwt;

    public AuthenticationResponse(Boolean isNew, String jwt) {
        this.isNew = isNew;
        this.jwt = jwt;
    }
}
