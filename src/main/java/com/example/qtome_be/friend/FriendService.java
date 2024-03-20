package com.example.qtome_be.friend;


import com.example.qtome_be.member.MemberResponse;
import com.example.qtome_be.follow.Follow;
import com.example.qtome_be.follow.FollowAdaptor;
import com.example.qtome_be.follow.FollowResponse;
import com.example.qtome_be.follow.FollowStatus;
import com.example.qtome_be.member.Member;
import com.example.qtome_be.member.MemberAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendService {
    @Autowired
    private FriendAdaptor friendAdaptor;
    @Autowired
    private FollowAdaptor followAdaptor;
    @Autowired
    private MemberAdaptor memberAdaptor;

    @Transactional
    public void followFriend(String memberEmail, String email) {
        Member master = memberAdaptor.memberFind(memberEmail);

        Member slave = memberAdaptor.memberFind(email);
        followAdaptor.createFollow(master, slave);
        createFriend(master, slave);
    }

    public void createFriend(Member master, Member slave) {
        friendAdaptor.createFriend(master, slave);
    }


    public List<FollowResponse> findAllRequests(String memberEmail) {
        Member user = memberAdaptor.memberFind(memberEmail);

        List<Follow> follows = followAdaptor.findAll(user);
        return FollowResponse.toReponses(follows);
    }

    @Transactional
    public void acceptFollow( String memberEmail , Long id) {
        Member user = memberAdaptor.memberFind(memberEmail);

        Follow follow = followAdaptor.findById(id);
        follow.setFollowStatus(FollowStatus.ACCEPTED);
        friendAdaptor.createFriend(user, follow.getSender());
    }

    public List<MemberResponse> findFollwers(String memberEmail) {
        Member user = memberAdaptor.memberFind(memberEmail);

        List<Friend> follwers = friendAdaptor.findFollwers(user);
        return MemberResponse.toReponses(follwers.stream().map(Friend::getMaster).collect(Collectors.toList()));
    }

    public List<MemberResponse> findFollwings(String memberEmail) {
        Member user = memberAdaptor.memberFind(memberEmail);
        List<Friend> followings = friendAdaptor.findFollowings(user);
        return MemberResponse.toReponses(followings.stream().map(Friend::getMaster).collect(Collectors.toList()));
    }
}
