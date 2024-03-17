package com.example.qtome_be.friend;

import com.example.qtome_be.member.MemberResponse;
import com.example.qtome_be.config.TokenExtractor;
import com.example.qtome_be.follow.FollowResponse;
import io.swagger.annotations.ApiOperation;
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


    @PostMapping
    public ResponseEntity followFriend(HttpServletRequest request,@RequestParam String friendEmail) {
        String memberEmail = tokenExtractor.extractToken(request);

        friendService.followFriend(memberEmail, friendEmail);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "로그인 한 회원의 모든 친구 요청 조회")
    @GetMapping("/request/all")
    public ResponseEntity findAllRequests(HttpServletRequest request) {
        String memberEmail = tokenExtractor.extractToken(request);

        List<FollowResponse> response= friendService.findAllRequests(memberEmail);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "친구 요청 수락")
    @PostMapping("/request/accepted")
    public ResponseEntity acceptFollow(HttpServletRequest request,@RequestParam Long id) {
        String memberEmail = tokenExtractor.extractToken(request);

        friendService.acceptFollow(memberEmail, id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "나를 팔로우 한 모든 유저 조회")
    @GetMapping("/follwers")
    public ResponseEntity findAllFollwers(HttpServletRequest request) {
        String memberEmail = tokenExtractor.extractToken(request);

        List<MemberResponse> response= friendService.findFollwers(memberEmail);
        return ResponseEntity.ok().body(response);
    }

    @ApiOperation(value = "내가 팔로우 하는 모든 유저 조회")
    @GetMapping("/follwings")
    public ResponseEntity findAllFollwings(HttpServletRequest request) {
        String memberEmail = tokenExtractor.extractToken(request);

        List<MemberResponse> response= friendService.findFollwings(memberEmail);
        return ResponseEntity.ok().body(response);
    }
}
