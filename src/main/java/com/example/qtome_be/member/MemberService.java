package com.example.qtome_be.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberAdaptor memberAdaptor;
    public Member memberFind(String email) {
        return memberAdaptor.memberFind(email);
    }
    public Boolean existMember(String email) {
        return memberAdaptor.existMember(email);
    }

    public Member memberGet(AuthenticationRequest authenticationRequest) {
//        try {
            return memberAdaptor.memberFind(authenticationRequest.getEmail());
//        } catch (RuntimeException e) {
//            return memberAdaptor.memberCreate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
//        }

    }


    @Transactional
    public void modifyMemberInfo(String memberEmail, MemberInformationModifyRequest request) {
        Member user = memberAdaptor.memberFind(memberEmail);
        user.modify(request.getNickname(),request.getThumbnail());
    }

    public List<MemberResponse> searchMember(String request) {
        List<Member> memberList = memberAdaptor.searchMember(request);
        return MemberResponse.toReponses(memberList);
    }
}
