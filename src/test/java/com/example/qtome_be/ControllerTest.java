package com.example.qtome_be;


import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.example.qtome_be.config.TokenExtractor;
import com.example.qtome_be.config.security.JwtTokenUtil;
import com.example.qtome_be.member.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.resourceDetails;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(MemberController.class)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
public class ControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MemberService memberService;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;
    @MockBean
    private TokenExtractor tokenExtractor;

    @Test
    @WithMockUser
    void create() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("example@naver.com", "1234");

        Member member = Member.builder().email("example@naver.com").nickname("nickname").thumbnail("url").build();
        AuthenticationResponse response = new AuthenticationResponse(true, "asdasdawa-awdadasawd-asdasdwadawd");
        when(memberService.existMember(any())).thenReturn(Boolean.TRUE);
        when(memberService.memberGet(any())).thenReturn(member);
        when(jwtTokenUtil.generateToken("idpwd", "example@naver.com")).thenReturn("a");
        String requestBpdy = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/member/authenticate").with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Content-Length", String.valueOf(requestBpdy.getBytes(StandardCharsets.UTF_8).length))
                        .content(requestBpdy))
                .andExpect(status().isOk())
                .andDo(document("authenticate",
                        requestFields(
                                fieldWithPath("email").type(JsonFieldType.STRING).description("가입할 이메일 (중복x)"),
                                fieldWithPath("password").type(JsonFieldType.STRING).description("가입할 비밀번호 (아직은 조건 없)")
                        ),
                        responseFields(
                                fieldWithPath("isNew").type(JsonFieldType.BOOLEAN).description("신규 가입자 여부"),
                                fieldWithPath("jwt").type(JsonFieldType.STRING).description("jwt 토큰 (유효기간 10년)")
                        )
                ));
    }


}
