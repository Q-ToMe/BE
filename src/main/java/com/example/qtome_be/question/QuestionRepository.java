package com.example.qtome_be.question;

import com.example.qtome_be.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findAllByMember(Member member);
}
