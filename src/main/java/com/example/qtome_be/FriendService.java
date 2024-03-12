package com.example.qtome_be;


import com.example.qtome_be.member.Member;
import com.example.qtome_be.member.MemberAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void followFriend(Long userId, Long friendId) {
        Member master = memberAdaptor.memberFind(userId);
        Member slave = memberAdaptor.memberFind(friendId);
        followAdaptor.createFollow(master, slave);
        createFriend(master, slave);
    }

    public void createFriend(Member master, Member slave) {
        friendAdaptor.createFriend(master, slave);
    }


    public List<FollowResponse> findAllRequests(Long userId) {
        Member member = memberAdaptor.memberFind(userId);
        List<Follow> follows = followAdaptor.findAll(member);
        return FollowResponse.toReponses(follows);
    }

    @Transactional
    public void acceptFollow(Long userId, Long id) {
        Member member = memberAdaptor.memberFind(userId);
        Follow follow = followAdaptor.findById(id);
        follow.setFollowStatus(FollowStatus.ACCEPTED);
        friendAdaptor.createFriend(member, follow.getSender());
    }

    public List<MemberResponse> findFollwers(Long userId) {
        Member member = memberAdaptor.memberFind(userId);
        List<Friend> follwers = friendAdaptor.findFollwers(member);
        return MemberResponse.toReponses(follwers.stream().map(Friend::getMaster).collect(Collectors.toList()));
    }

    public List<MemberResponse> findFollwings(Long userId) {
        Member member = memberAdaptor.memberFind(userId);
        List<Friend> followings = friendAdaptor.findFollowings(member);
        return MemberResponse.toReponses(followings.stream().map(Friend::getMaster).collect(Collectors.toList()));
    }
}
