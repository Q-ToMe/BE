package com.example.qtome_be.member;

import com.example.qtome_be.config.TokenExtractor;
import com.example.qtome_be.config.security.JwtTokenUtil;
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
    @GetMapping
    public ResponseEntity test(HttpServletRequest request) {
        String email = tokenExtractor.extractToken(request);
        System.out.println(email);
        return ResponseEntity.ok("ok!!");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        final Member member = memberService.memberGet(authenticationRequest);
        final String jwt = jwtTokenUtil.generateToken("idpwd",member.getEmail());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
