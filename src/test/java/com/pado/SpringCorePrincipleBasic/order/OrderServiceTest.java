package com.pado.SpringCorePrincipleBasic.order;

import com.pado.SpringCorePrincipleBasic.member.Grade;
import com.pado.SpringCorePrincipleBasic.member.Member;
import com.pado.SpringCorePrincipleBasic.member.MemberService;
import com.pado.SpringCorePrincipleBasic.member.MemberServiceImpl;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "mA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "iA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
