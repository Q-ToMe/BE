package com.example.qtome_be.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepositoryCustom{
    @Autowired
    private  JPAQueryFactory queryFactory;

    private QMember member = QMember.member;
    @Override
    public Member find() {
        return queryFactory.selectFrom(member).fetchOne();
    }
}
