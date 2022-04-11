package com.pado.SpringCorePrincipleBasic.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {
    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SinglesnBean.class);
        SinglesnBean bean = ac.getBean(SinglesnBean.class);
        SinglesnBean bean2 = ac.getBean(SinglesnBean.class);

        System.out.println("bean = " + bean);
        System.out.println("bean2 = " + bean2);
        Assertions.assertThat(bean).isSameAs(bean2);

        ac.close();
    }

    @Scope("singleton")
    static class SinglesnBean{
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
