package com.example.qtome_be.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class AuthenticationResponse {
    @Schema(description = "jwt 토큰 (유효기간 10년)", example = "asdasdawa-awdadasawd-asdasdwadawd", required = true)
    private final String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
