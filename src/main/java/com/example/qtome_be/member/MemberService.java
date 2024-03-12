package com.example.qtome_be.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberAdaptor memberAdaptor;
    public Member memberFind(Long id) {
        return memberAdaptor.memberFind(id);
    }




}
