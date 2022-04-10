package com.pado.SpringCorePrincipleBasic.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    // 이런식으로 하면  DIP가 안지켜짐. 레퍼지토리를 바꾸고 싶을 때 서비스 코드가 바뀐다.
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    // App Config 도입하여 리펙토링
    // final이니까 초기화 해야함


    public final MemberRepository memberRepository;

    // 생성자를 만들어놓는다. 생성자를 통해 구현체가 뭐가 들어갈지를 정해줄 것
    // 이제 MemoryMemberRepository는 직접 생성할 필요 없다.
    // AppCOnfig에서 생성해서 넣어줄 것. 이를 생성자 주입이라고 한다.
    @Autowired // ac.getBean(MemberRepository.class) 가 자동으로 주입된다고 생각하면 됨
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 싱글톤 테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
