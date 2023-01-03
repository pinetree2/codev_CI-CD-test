package com.example.springstudy.service;

import com.example.springstudy.domain.Member;
import com.example.springstudy.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

//테스트 껍데기만드는 단축키 ctrl+shift+t
class MemberServiceTest {

    MemberService memberService ;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository(); //지금 다른리포지토리를 사용한거임

    @BeforeEach
    public void beforeEach(){
        //테스트 실행할때마다 각각 생성
        //같은 메모리레포지토리를 쓰기위해 직접 new 하지않고 외부에서 넣어주는것 -> dependency injection
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given 무언가가 주어졌을때
        Member member = new Member();
        member.setName("hello");

        //when 이걸 실행했을때
        Long saveId = memberService.join(member);

        //then 결과가 이게 나온다
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());


    }


    @Test
    public void duplicate_user_exception(){

        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); //중복

        //when
        memberService.join(member1);

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

      //  memberService.join(member2);


        //try - catch 로 예외처리
        /*
       try{
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/


    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}