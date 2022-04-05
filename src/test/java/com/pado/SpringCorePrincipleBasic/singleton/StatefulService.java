package com.pado.SpringCorePrincipleBasic.singleton;

public class StatefulService {

    private int price; // 상태를 유지하는 필드

    /*public void order(String name, int price) {
        System.out.println("name= " +  name + " price = " + price);
        this.price = price; // 여기가 문제!!
    }*/

    public int order(String name, int price) {
        System.out.println("name= " +  name + " price = " + price);
        // this.price = price; // 여기가 문제!!
        return price; // 이렇게 price를 넘겨버리면 됨
     }

    /*public int getPrice(){
        return price;
    }*/
}
