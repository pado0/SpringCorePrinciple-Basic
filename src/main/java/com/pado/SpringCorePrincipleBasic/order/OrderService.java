package com.pado.SpringCorePrincipleBasic.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice); // 주문생성 함수
}
