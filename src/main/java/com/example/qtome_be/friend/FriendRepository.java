package com.example.qtome_be.friend;

import com.example.qtome_be.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend,Long> {
    List<Friend> findAllBySlave(Member member);

    List<Friend> findAllByMaster(Member member);
}
