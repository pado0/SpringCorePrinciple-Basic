package com.pado.SpringCorePrincipleBasic.order;

import com.pado.SpringCorePrincipleBasic.annotation.MainDiscountPolicy;
import com.pado.SpringCorePrincipleBasic.discount.DiscountPolicy;
import com.pado.SpringCorePrincipleBasic.discount.FixDiscountPolicy;
import com.pado.SpringCorePrincipleBasic.discount.RateDiscountPolicy;
import com.pado.SpringCorePrincipleBasic.member.Member;
import com.pado.SpringCorePrincipleBasic.member.MemberRepository;
import com.pado.SpringCorePrincipleBasic.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // final이 붙은 필수 변수를 기준으로 생성자를 만들어줌, autowired는 자동으로 붙으니 생략가능? 왜안되지? ->자동 생성이라 순서도 중요하구나..!
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 이렇게 코드수정이 들어간다.

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; // 인터페이스에만 의존하도록 변경, 이렇게만 하면 null pointer exception ㅋㅋㅋ


    // 철저하게 인터페이스에만 의존하고있다. 너무 좋다. 완벽한 DIP. FIxdiscount를 사용할지 Rate를 사용할지 알바 없다.
    /*@Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/


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

