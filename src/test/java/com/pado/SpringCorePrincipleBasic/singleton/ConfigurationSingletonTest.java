package com.pado.SpringCorePrincipleBasic.singleton;

import com.pado.SpringCorePrincipleBasic.AppConfig;
import com.pado.SpringCorePrincipleBasic.member.MemberRepository;
import com.pado.SpringCorePrincipleBasic.member.MemberServiceImpl;
import com.pado.SpringCorePrincipleBasic.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 테스트로 만들어놓은 함수 꺼내려고 임플로 호출
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // new로 생성해놨지만 같은 bean 객체를 호출한다.
        // 어떻게..?!?!??
        System.out.println("memberRepository1 = " + memberRepository1);
        System.out.println("memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);
        Assertions.assertThat(memberRepository1).isSameAs(memberRepository2);

    }
    
    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean.getClass() = " + bean.getClass());
        //bean.getClass() = class com.pado.SpringCorePrincipleBasic.AppConfig$$EnhancerBySpringCGLIB$$20049e46 > 이렇게 리턴된다. 원래 클래스 명만 리턴되어야 함. why?
    }
}
