package com.pado.SpringCorePrincipleBasic.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA: A사용자가 10000원 주문
        //statefulService1.order("userA", 10000);

        // 객체에 state를 저장하지 말고 price를 리턴하자
        int userAPrice = statefulService1.order("userA", 10000);

        //ThreadB: B사용자가 20000원 주문
        //클라이언트가 값을 변경할 수 있는 필드를 만들어버림.
        int userBPrice = statefulService2.order("userB", 20000);

        //int price1 = statefulService1.getPrice();
        //int price2 = statefulService2.getPrice();

        // 싱글톤이라 price1=20000이 됨. 서비스 망 ㅠㅠ!!!
        //System.out.println(price1+ " , " +price2);

        //assertThat(statefulService1.getPrice()).isEqualTo(20000);
        assertThat(userAPrice).isEqualTo(10000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}