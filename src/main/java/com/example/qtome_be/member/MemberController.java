package com.example.qtome_be.member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
public class MemberController {

    @GetMapping
    public ResponseEntity test() {
        return ResponseEntity.ok("ok!!");
    }
}