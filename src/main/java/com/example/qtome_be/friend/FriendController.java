package com.example.qtome_be.friend;

import com.example.qtome_be.member.MemberResponse;
import com.example.qtome_be.config.TokenExtractor;
import com.example.qtome_be.follow.FollowResponse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("friend")
public class FriendController {

    @Autowired
    private FriendService friendService;
    @Autowired
    private TokenExtractor tokenExtractor;


    @ApiOperation(value = "친구 신청 api")
    @ApiImplicitParams(
            {@ApiImplicitParam(
                    name = "friendEmail"
                    , value = "친구 신청을 보내고자 하는 친구의 이메일 주소"
                    , required = true
                    , dataType = "String"
                    , paramType = "query"
                    , defaultValue = ""
            )
            })
    @PostMapping
    public ResponseEntity followFriend(HttpServletRequest request,@RequestParam String friendEmail) {
        String memberEmail = tokenExtractor.extractToken(request);

        friendService.followFriend(memberEmail, friendEmail);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "로그인 한 회원의 모든 친구 요청 리스트 조회")
    @GetMapping("/request/all")
    public ResponseEntity<List<FollowResponse>> findAllRequests(HttpServletRequest request) {
        String memberEmail = tokenExtractor.extractToken(request);

        List<FollowResponse> response= friendService.findAllRequests(memberEmail);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "친구 요청 수락",notes = "친구 요청 리스트 조회 이후 사용하는 api")
    @ApiImplicitParams(
            {@ApiImplicitParam(
                            name = "id"
                            , value = "친구 요청 시퀀스 ( 친구 요청 조회 api에서 id 가져올 수 있음 )"
                            , required = true
                            , dataType = "Long (int처럼 그냥 숫자만 보내면 됨. TS를 고려한 타입 명시)"
                            , paramType = "query"
                            , defaultValue = ""
                    )
            })
    @PostMapping("/request/accepted")
    public ResponseEntity acceptFollow(HttpServletRequest request,@RequestParam Long id) {
        String memberEmail = tokenExtractor.extractToken(request);

        friendService.acceptFollow(memberEmail, id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "나를 팔로우 한 모든 유저 조회")
    @GetMapping("/followers")
    public ResponseEntity<List<MemberResponse>> findAllFollwers(HttpServletRequest request) {
        String memberEmail = tokenExtractor.extractToken(request);

        List<MemberResponse> response= friendService.findFollwers(memberEmail);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "내가 팔로우 하는 모든 유저 조회")

    @GetMapping("/followings")
    public ResponseEntity<List<MemberResponse>> findAllFollwings(HttpServletRequest request) {
        String memberEmail = tokenExtractor.extractToken(request);

        List<MemberResponse> response= friendService.findFollwings(memberEmail);
        return ResponseEntity.ok().body(response);
    }
}
