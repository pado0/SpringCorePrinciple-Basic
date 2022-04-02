package com.pado.SpringCorePrincipleBasic;

import com.pado.SpringCorePrincipleBasic.member.Grade;
import com.pado.SpringCorePrincipleBasic.member.Member;
import com.pado.SpringCorePrincipleBasic.member.MemberService;
import com.pado.SpringCorePrincipleBasic.member.MemberServiceImpl;
import com.pado.SpringCorePrincipleBasic.order.Order;
import com.pado.SpringCorePrincipleBasic.order.OrderService;
import com.pado.SpringCorePrincipleBasic.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println(order);
        System.out.println(order.calculatePrice());
    }
}
