package com.pado.SpringCorePrincipleBasic.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

// MyLogger는 http request가 들어와야 빈으로 관리되는데 아직 요청이 들어오지 않음 -> Provider로 해
@Component
@Scope(value = "request") // http 요청당 하나씩 생성.
public class MyLogger {

    private String uuid;
    private String requestURL; // 중간에 들어오도록 세팅

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "["+ requestURL + "]"+message);
    }

    // 빈 생성 시점에 uuid가 저장됨
    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString(); // 글로벌 유니크 uuid가 생성됨
        System.out.println("[" + uuid + "] reqeust scope bean create: " + this);
    }

    // 고객요청이 종료될 때 출력됨
    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] reqeust scope bean close: " + this);
    }

}