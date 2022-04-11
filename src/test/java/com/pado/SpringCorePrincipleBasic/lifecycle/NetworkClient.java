package com.pado.SpringCorePrincipleBasic.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient{// implements InitializingBean, DisposableBean {
    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출 url = " + url);
        connect();
        call("초기화 연결 메세지");

    }

    public void setUrl(String url) {
        System.out.println("url setting");
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect(){
        System.out.println("연결됨 : "  + url);

    }

    // url에 콜을 했고, 메세지가 무엇인지 호출
    public void call(String message){
        System.out.println("call" + url+ " message "+message);
    }

    // 서비스 종료시 호출
    public void disconnect(){
        System.out.println("서비스 종료" + url);
    }

 /*   @Override
    public void afterPropertiesSet() throws Exception { // 의존관계 주입이 끝나면 호출하겠다.
        connect();
        call("의존관계 주입 후 연결 메세지");

    }

    @Override
    public void destroy() throws Exception {
        System.out.println("빈 종료시 호출");
        disconnect();
    }*/

    @PostConstruct
    public void init () throws Exception { // 의존관계 주입이 끝나면 호출하겠다.
        call("의존관계 주입 후 연결 메세지");
        connect();
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("빈 종료시 호출");
        disconnect();
    }
}

