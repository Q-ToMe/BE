package com.example.qtome_be.follow;

import com.example.qtome_be.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow,Long> {
    List<Follow> findAllByReceiver(Member member);
}
