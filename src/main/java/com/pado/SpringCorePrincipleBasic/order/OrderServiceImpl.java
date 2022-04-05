package com.pado.SpringCorePrincipleBasic.order;

import com.pado.SpringCorePrincipleBasic.discount.DiscountPolicy;
import com.pado.SpringCorePrincipleBasic.discount.FixDiscountPolicy;
import com.pado.SpringCorePrincipleBasic.discount.RateDiscountPolicy;
import com.pado.SpringCorePrincipleBasic.member.Member;
import com.pado.SpringCorePrincipleBasic.member.MemberRepository;
import com.pado.SpringCorePrincipleBasic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 이렇게 코드수정이 들어간다.

    private final DiscountPolicy discountPolicy; // 인터페이스에만 의존하도록 변경, 이렇게만 하면 null pointer exception ㅋㅋㅋ
    private final MemberRepository memberRepository;

    // 철저하게 인터페이스에만 의존하고있다. 너무 좋다. 완벽한 DIP. FIxdiscount를 사용할지 Rate를 사용할지 알바 없다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    // 주문이 들어오면 회원정보를 저장소에서 찾고
    // 할인정보를 멤버를 넘겨서 받아오고
    // 주문을 만들어 생성 완
   @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인에 관한건 DiscountPolicy에 일임. 단일책임원칙이 잘 지켜짐.
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}

