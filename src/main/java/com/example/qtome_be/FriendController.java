package com.example.qtome_be;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("friend")
public class FriendController {

    @Autowired
    private FriendService friendService;


    @PostMapping
    public ResponseEntity followFriend(@RequestParam Long userId, @RequestParam Long friendId) {
        friendService.followFriend(userId, friendId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/request/all")
    public ResponseEntity findAllRequests(@RequestParam Long userId) {
        List<FollowResponse> response= friendService.findAllRequests(userId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/request/accepted")
    public ResponseEntity acceptFollow(@RequestParam Long userId, @RequestParam Long id) {
        friendService.acceptFollow(userId, id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/follwers")
    public ResponseEntity findAllFollwers(@RequestParam Long userId) {
        List<MemberResponse> response= friendService.findFollwers(userId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/follwings")
    public ResponseEntity findAllFollwings(@RequestParam Long userId) {
        List<MemberResponse> response= friendService.findFollwings(userId);
        return ResponseEntity.ok().body(response);
    }
}
