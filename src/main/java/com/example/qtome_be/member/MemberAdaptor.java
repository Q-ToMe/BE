package com.example.qtome_be.member;

import org.springframework.beans.factory.annotation.Autowired;

@Adaptor
public class MemberAdaptor {


    @Autowired
    private MemberRepository memberRepository;
    public Member memberFind(Long id) {
        return memberRepository.findById(id).orElseThrow(()-> new RuntimeException(String.format("user[%s] 유저를  찾을 수 없습니다", id)));
    }

}
