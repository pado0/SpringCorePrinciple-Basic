package com.pado.SpringCorePrincipleBasic.singleton;

public class SingletonService {

    // static으로 선언하면 클래스레벨에 존재하기 때문에 딱 하나만 생성됨.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;
    }

    // private 생성자로 생성을 막는다
    private SingletonService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
