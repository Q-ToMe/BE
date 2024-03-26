package com.example.qtome_be.member;

import com.example.qtome_be.config.TokenExtractor;
import com.example.qtome_be.config.security.JwtTokenUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("member")
@CrossOrigin("*")
public class MemberController {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MemberService memberService;

    @Autowired
    private TokenExtractor tokenExtractor;

//    @ApiOperation(value = "테스트 api")
    @GetMapping
    public ResponseEntity test(HttpServletRequest request) {
        String email = tokenExtractor.extractToken(request);
        System.out.println(email);
        return ResponseEntity.ok("ok!!");
    }

    @Tag(name = "회원가입 and 로그인", description = "이 api는 회원가입과 로그인 둘 다 쓰이는 api입니다.(후에 분리 예정)</br> " +
            "이 api가 호출되면 jwt토큰과 신규 유저 여부가 반환됩니다.</br> " +
            "isNew가 참이면 \"유저 정보 입력 api\"를 호출해서 유저 정보를 마저 입력할 수 있도록 해주세요.</br></br>" +
            "백엔드는  jwt 토큰을 기준으로 로그인 한 유저를 구별할 수 있습니다.</br>" +
            "클라이언트(FE)에서는 반환값을 저장해두었다가, 로그인 한 유저의 정보가 필요한 api를 요청할 때에는 헤더에 Bearer Token으로 넣어주시면 됩니다.</br>" +
            "예시)</br>" +
            "헤더 이름: Authorization</br>" +
            "헤더 값: \"Bearer {토큰}\"</br>" +
            "`Bearer 다음에 띄어쓰기가 있는것에 유의해주세요.`")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        Boolean isNew = !memberService.existMember(authenticationRequest.getEmail());
        final Member member = memberService.memberGet(authenticationRequest);
        final String jwt = jwtTokenUtil.generateToken("idpwd", member.getEmail());
        return ResponseEntity.ok(new AuthenticationResponse(isNew,jwt));
    }

//    @ApiOperation(value = "유저 정보 입력 api", notes = "방금 가입한 유저가 상세 정보를 입력할 수 있는 api")
    @PutMapping("/info")
    public ResponseEntity modifyMemberInfo(HttpServletRequest httpRequest,@RequestBody MemberInformationModifyRequest request){
        String memberEmail = tokenExtractor.extractToken(httpRequest);

        memberService.modifyMemberInfo(memberEmail,request);
        return ResponseEntity.ok().build();
    }

//    @ApiOperation(value = "유저 검색 api", notes = "심화적인 검색기능이 아닌 일반적인 like 쿼리 사용")
    @GetMapping("/search")
    public ResponseEntity<List<MemberResponse>> searchMember(@RequestParam String value){

        List<MemberResponse> response = memberService.searchMember(value);
        return ResponseEntity.ok(response);
    }
}
