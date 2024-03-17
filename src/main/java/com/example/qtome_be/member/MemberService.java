package com.example.qtome_be.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberAdaptor memberAdaptor;
    public Member memberFind(String email) {
        return memberAdaptor.memberFind(email);
    }

    public Member memberGet(AuthenticationRequest authenticationRequest) {
        try {
            return memberAdaptor.memberFind(authenticationRequest.getEmail());
        } catch (RuntimeException e) {
            return memberAdaptor.memberCreate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        }

    }




}
