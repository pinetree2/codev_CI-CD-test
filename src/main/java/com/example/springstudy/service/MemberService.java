package com.example.springstudy.service;

import com.example.springstudy.domain.Member;
import com.example.springstudy.repository.MemberRepository;
import com.example.springstudy.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //서비스 어노테이션
//비즈니스에 의존하는 클래스
public class MemberService {

    private final MemberRepository memberRepository ; //다른 객체를 생성하지 않고 생성자 생성

    @Autowired //이거를 해주면 생성자에 memberRepository가 필요한거 확인을 하고 컨테이너에 멤버리포지토리를 넣어줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입 */
    public Long join(Member member){

        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    //같은 이름이 있는 중복 회원 x
    //optional 으로 감싸면 여러 메소드 사용가능
    //if null 이런거 대신에 쓸수있는
    //ctrl + alt + v 단축키
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    /**
     * 전체 회원조회
     * */
    public List<Member> findMembers(){
        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);


    }
}
