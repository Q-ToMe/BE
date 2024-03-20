package com.example.qtome_be.follow;


import com.example.qtome_be.config.Adaptor;
import com.example.qtome_be.member.Member;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Adaptor
public class FollowAdaptor {
    @Autowired
    private FollowRepository followRepository;

    public void createFollow(Member master, Member slave) {
        Follow follow = Follow.builder().sender(master).receiver(slave).followStatus(FollowStatus.INITIAL).build();
        followRepository.save(follow);
    }

    public List<Follow> findAll(Member member) {
        return followRepository.findAllByReceiver(member);
    }

    public Follow findById(Long id) {
        return followRepository.findById(id).get();
    }

}
