package com.pado.SpringCorePrincipleBasic.member;

public class MemberServiceImpl implements MemberService{

    // 이런식으로 하면  DIP가 안지켜짐. 레퍼지토리를 바꾸고 싶을 때 서비스 코드가 바뀐다.
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
