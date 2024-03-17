package com.example.qtome_be.member;

import org.springframework.beans.factory.annotation.Autowired;

@Adaptor
public class MemberAdaptor {


    @Autowired
    private MemberRepository memberRepository;
    public Member memberFind(String email) {
        return memberRepository.findByEmail(email).orElseThrow(()-> new RuntimeException(String.format("user[%s] 유저를  찾을 수 없습니다", email)));
    }

    public Member memberCreate(String email,String password) {
        Member member = Member.builder().email(email).password(password).build();
        return memberRepository.save(member);
    }
}
