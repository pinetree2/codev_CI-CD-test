package com.example.springstudy.repository;

import com.example.springstudy.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    //Optional 은 null을 처리하기 위해 감싸서 .. 하는
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
