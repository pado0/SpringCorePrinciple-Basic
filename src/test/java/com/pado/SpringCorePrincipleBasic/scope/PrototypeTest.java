package com.pado.SpringCorePrincipleBasic.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototype bean 1");
        PrototypeBean bean = ac.getBean(PrototypeBean.class);
        System.out.println("find prototype bean 2");
        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);

        System.out.println("bean = " + bean);
        System.out.println("bean2 = " + bean2);
        Assertions.assertThat(bean).isNotSameAs(bean2);

        ac.close(); // close 해도 destory 호출 x
    }

    @Scope("prototype")
    static class PrototypeBean{
            @PostConstruct
            public void init(){
                System.out.println("init");
            }

            @PreDestroy
            public void destroy(){
                System.out.println("destroy");
            }
    }
}
