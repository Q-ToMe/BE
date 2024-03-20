package com.example.qtome_be.member;

import com.example.qtome_be.config.Adaptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Adaptor
public class MemberAdaptor {


    @Autowired
    private MemberRepository memberRepository;
    public Member memberFind(String email) {
        return memberRepository.findByEmail(email).orElseThrow(()-> new RuntimeException(String.format("user[%s] 유저를  찾을 수 없습니다", email)));
    }

    public Boolean existMember(String email) {
        return memberRepository.existsByEmail(email);
    }

    public Member memberCreate(String email,String password) {
        Member member = Member.builder().email(email).password(password).build();
        return memberRepository.save(member);
    }

    public List<Member> searchMember(String request) {
        return memberRepository.findByNicknameContains(request);
    }
}
