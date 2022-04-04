package com.pado.SpringCorePrincipleBasic.beanfind;

import com.pado.SpringCorePrincipleBasic.AppConfig;
import com.pado.SpringCorePrincipleBasic.member.MemberService;
import com.pado.SpringCorePrincipleBasic.member.MemberServiceImpl;
import org.hibernate.resource.beans.container.internal.NoSuchBeanException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }


    @Test
    @DisplayName("빈 이름 없이 타입으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class); // 인터페이스로 조회하면 인터페이스의 구현체가 대상이 됨.
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class); // 구체 타입으로도 조회 가능, 역할에 의존하도록 해야해서 별로 좋은 코드는 아님.
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 실패")
    void findBeanByNameX(){
        //MemberService memberService = ac.getBean("xxxx", MemberService.class); //.NoSuchBeanDefinitionException
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxx", MemberService.class)); // 오른쪽 애가 실행될 때, 왼쪽 Exception이 터지면 성공!
    }
}
