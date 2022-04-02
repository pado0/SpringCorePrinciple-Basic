package com.pado.SpringCorePrincipleBasic.order;

import com.pado.SpringCorePrincipleBasic.discount.DiscountPolicy;
import com.pado.SpringCorePrincipleBasic.discount.FixDiscountPolicy;
import com.pado.SpringCorePrincipleBasic.member.Member;
import com.pado.SpringCorePrincipleBasic.member.MemberRepository;
import com.pado.SpringCorePrincipleBasic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    // 주문이 들어오면 회원정보를 저장소에서 찾고
    // 할인정보를 멤버를 넘겨서 받아오고
    // 주문을 만들어 생성 완
   @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인에 관한건 DiscountPolicy에 일임. 단일책임원칙이 잘 지켜짐.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
