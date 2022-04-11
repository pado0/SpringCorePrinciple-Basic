package com.pado.SpringCorePrincipleBasic.scope;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

public class SingletonWithPrototype1 {
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean bean = ac.getBean(PrototypeBean.class);
        bean.addCount();
        Assertions.assertThat(bean.getCount()).isEqualTo(1);

        PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
        bean2.addCount();
        Assertions.assertThat(bean2.getCount()).isEqualTo(1);
    }
    
    
    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean bean = ac.getBean(ClientBean.class);
        int count = bean.logic();
        Assertions.assertThat(count).isEqualTo(1);

        // 싱글톤 빈 호출해도 프로토타입 새로 생성되지 않는다. 이미 주입되어있기 때문.
        ClientBean bean2 = ac.getBean(ClientBean.class);
        int count2 = bean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean{
        //private final PrototypeBean prototypeBean; // 생성시점에 주입. 다시 생성되지 않으니 다른 값으로 재주입 되지 않음.

        @Autowired
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider;
        private Provider<PrototypeBean> prototypeBeanProvider;

       /* @Autowired
        ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }
        */

        public int logic(){
            // 프로바이더. getObject를 호출하면 그때서야 프로토타입 빈을 찾아온다. 딱 빈을 찾아주는 기능만 제공.
            // 필요할 때 마다 스프링 컨테이너 조회. 근데 아예 프로토타입 빈이 생성되지 않는데 조회해서 뭐해? 조회한다고 해서 다른 빈이 조회되는 이유는 뭐야? 여러 개 중에 같은걸 찾아올 수 도 있짆아.?
            // 프로토타입 빈은 조회시마다 새로생긴다.
            PrototypeBean prototypeBean = prototypeBeanProvider.get();//getObject();
            prototypeBean.addCount();

            int count = prototypeBean.getCount();
            return count;
        }
    }


    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("init : " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("destroy");
        }
    }
}
