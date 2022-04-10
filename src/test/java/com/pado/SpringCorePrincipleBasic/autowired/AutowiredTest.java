package com.pado.SpringCorePrincipleBasic.autowired;

import com.pado.SpringCorePrincipleBasic.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    // 자동 주입할 대상이 없을 경우
    static class TestBean {

        @Autowired(required = false)
        //스프링 컨테이너에서 관리되지 않는 아무거나 비어있는 빈을 가져옴. Member는 스플이 빈이 아니니까 null인것.
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1); // 의존관계가 없으면 메소드 자체를 실행을 안함
        }
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2); // null로 리턴
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3); //
        }
    }

}
