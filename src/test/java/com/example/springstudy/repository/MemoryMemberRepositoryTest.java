package com.example.springstudy.repository;

import com.example.springstudy.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
        //끝날때마다 비움
        //테스트는 서로 의존관계가 없ㅇ므
        
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("haesong");

        repository.save(member); //저장하면서 id세팅
        Member result = repository.findById(member.getId()).get(); //optional 에서 값을 꺼낼때는 .get해야함
        Assertions.assertEquals(member,result); //test pass 여부 방법1
        assertThat(member).isEqualTo(result); //방법2

        //System.out.println("result = " + (result  == member));
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result =  repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

}
