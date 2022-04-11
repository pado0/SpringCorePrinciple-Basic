package com.pado.SpringCorePrincipleBasic.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void LifeCycleTest(){
        // 보통 닫는건 Anno-config에서 잘 안함. 더 상위로 호출
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);

        System.out.println("----겟빈 전----");
        NetworkClient client = ac.getBean(NetworkClient.class);

        System.out.println("----닫기 전 ----");
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig{
        @Bean // (initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient(){

            // 객체를 먼저 만들어준 뒤
            NetworkClient networkClient = new NetworkClient();

            // 설정값이 들어와야할 때가 있다.
            System.out.println("----설정값 세팅 ----");

            // 세팅해줄 때 객체가 처음 사용되므로 초기화 된 상태인가보다. -> 이거 아님.
            networkClient.setUrl("http://hyo.xyz");

            System.out.println("----설정값 완료 ----");

            return networkClient;
        }
    }
}
