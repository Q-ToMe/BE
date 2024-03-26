package com.example.qtome_be.member;

import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepositoryCustom {
    Member find();
}
