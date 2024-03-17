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

    @ApiOperation(value = "회원가입 and 로그인")
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        final Member member = memberService.memberGet(authenticationRequest);
        final String jwt = jwtTokenUtil.generateToken("idpwd",member.getEmail());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
