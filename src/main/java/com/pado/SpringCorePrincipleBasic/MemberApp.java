package com.pado.SpringCorePrincipleBasic;

import com.pado.SpringCorePrincipleBasic.member.Grade;
import com.pado.SpringCorePrincipleBasic.member.Member;
import com.pado.SpringCorePrincipleBasic.member.MemberService;
import com.pado.SpringCorePrincipleBasic.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        // AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService(); // app config에서 memberservice를 받아옴
        //MemberService memberService = new MemberServiceImpl();

        // 스프링 컨테이너에 객체 생성한걸 집어넣어 관리한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);// 모든 객체를 관리
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP); // cmd +option + v 참조변수 선언해주는 단축키
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        // 만든 멤버랑 찾은 멤버 값 비교
        System.out.println(member.getName());
        System.out.println(findMember.getName());
    }
}
