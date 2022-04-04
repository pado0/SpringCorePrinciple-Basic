package com.pado.SpringCorePrincipleBasic.beanfind;

import com.pado.SpringCorePrincipleBasic.AppConfig;
import com.pado.SpringCorePrincipleBasic.member.MemberRepository;
import com.pado.SpringCorePrincipleBasic.member.MemberService;
import com.pado.SpringCorePrincipleBasic.member.MemberServiceImpl;
import com.pado.SpringCorePrincipleBasic.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class); //test에 만들어놓은 컨피그로 사용

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate(){
        //NoUniqueBeanDefinitionException 오류가 터짐
        //MemberRepository bean = ac.getBean(MemberRepository.class);
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class)); // 오른쪽 애가 실행될 때, 왼쪽 Exception이 터지면 성공!
    }

    @Test
    @DisplayName("타입 조회시 중복이라면, 빈 이름을 지정하면 됨")
    void findBeanByName() {
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 모두 조회하기") // 중복인 경우에 한번에 조회하는 방법.
    void findBeanByType() {
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "  value = " + beansOfType.get(key)); // key로 value 찾기
        }
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    // class 안에서만 쓸 클래스, 내부 클래스 바로 넘어가려했는데 바아아로 나와버리네
    @Configuration
    static class SameBeanConfig{

        @Bean
        public MemberRepository memberRepository(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }

}
