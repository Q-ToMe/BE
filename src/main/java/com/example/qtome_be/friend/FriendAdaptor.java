package com.example.qtome_be.friend;

import com.example.qtome_be.member.Adaptor;
import com.example.qtome_be.member.Member;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Adaptor

public class FriendAdaptor {
    @Autowired
    private FriendRepository friendRepository;


    public void createFriend(Member master, Member slave) {
        Friend friend = Friend.builder().master(master).slave(slave).build();
        friendRepository.save(friend);
    }

    public List<Friend> findFollwers(Member member) {
        return friendRepository.findAllBySlave(member);
    }
    public List<Friend> findFollowings(Member member) {
        return friendRepository.findAllByMaster(member);
    }
}
