package com.example.springstudy.repository;

import com.example.springstudy.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//구현체
@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L; //0,1,2 늘어남

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //get해서 받아온 id가 null값일 우려가 있기에
    }

    @Override
    public Optional<Member> findByName(String name) {
        //파라미터로 넘어온 name과 member에서get해서 가져온 name과 같은지 확인
        //values(),stream()이런건 Map 의 메소드라서 따로 검색해보자
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //하나라도 찾는다는 소리
        //끝까지 돌았는데 없으면 옵셔널에 null이 포함되어 반환된다.
    }

    @Override
    public List<Member> findAll() {
        //Map 을 List로 반환하려면?
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
        //싹 비움
    }
}
