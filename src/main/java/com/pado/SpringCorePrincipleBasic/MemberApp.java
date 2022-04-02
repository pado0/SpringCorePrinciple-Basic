package com.pado.SpringCorePrincipleBasic;

import com.pado.SpringCorePrincipleBasic.member.Grade;
import com.pado.SpringCorePrincipleBasic.member.Member;
import com.pado.SpringCorePrincipleBasic.member.MemberService;
import com.pado.SpringCorePrincipleBasic.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP); // cmd +option + v 참조변수 선언해주는 단축키
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        // 만든 멤버랑 찾은 멤버 값 비교
        System.out.println(member.getName());
        System.out.println(findMember.getName());
    }
}
