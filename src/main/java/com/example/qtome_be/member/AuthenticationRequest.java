package com.example.qtome_be.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {
    @Schema(description = "가입할 이메일 (중복x)", example = "example@naver.com", required = true)
    private String email;
    @Schema(description = "가입할 비밀번호 (아직은 조건 없)", example = "password1234", required = true)
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
