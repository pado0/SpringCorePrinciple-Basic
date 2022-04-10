package com.pado.SpringCorePrincipleBasic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setAge(12); // getter setter 자동으로 만들어줌
    }
}
