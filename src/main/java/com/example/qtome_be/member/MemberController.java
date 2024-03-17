package com.example.qtome_be.member;

import com.example.qtome_be.config.TokenExtractor;
import com.example.qtome_be.config.security.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("member")
public class MemberController {



    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TokenExtractor tokenExtractor;
    @ApiOperation(value = "테스트 api")
    @GetMapping
    public ResponseEntity test(HttpServletRequest request) {
        String email = tokenExtractor.extractToken(request);
        System.out.println(email);
        return ResponseEntity.ok("ok!!");
    }

    @ApiOperation(value = "회원가입 and 로그인",notes = "이 api는 회원가입과 로그인 둘 다 쓰이는 api입니다.(후에 분리 예정)</br> " +
            "이 api가 호출되면 jwt토큰이 반환됩니다.</br> " +
            "백엔드는 이 토큰을 기준으로 로그인 한 유저를 구별할 수 있습니다.</br>" +
            "클라이언트(FE)에서는 반환값을 저장해두었다가, 로그인 한 유저의 정보가 필요한 api를 요청할 때에는 헤더에 Bearer Token으로 넣어주시면 됩니다.</br>" +
            "예시)</br>" +
            "헤더 이름: Authorization</br>" +
            "헤더 값: \"Bearer {토큰}\"</br>" +
            "`Bearer 다음에 띄어쓰기가 있는것에 유의해주세요.`")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        final Member member = memberService.memberGet(authenticationRequest);
        final String jwt = jwtTokenUtil.generateToken("idpwd",member.getEmail());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
