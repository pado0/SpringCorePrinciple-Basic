package com.pado.SpringCorePrincipleBasic;

import com.pado.SpringCorePrincipleBasic.discount.DiscountPolicy;
import com.pado.SpringCorePrincipleBasic.discount.FixDiscountPolicy;
import com.pado.SpringCorePrincipleBasic.discount.RateDiscountPolicy;
import com.pado.SpringCorePrincipleBasic.member.MemberRepository;
import com.pado.SpringCorePrincipleBasic.member.MemberService;
import com.pado.SpringCorePrincipleBasic.member.MemberServiceImpl;
import com.pado.SpringCorePrincipleBasic.member.MemoryMemberRepository;
import com.pado.SpringCorePrincipleBasic.order.OrderService;
import com.pado.SpringCorePrincipleBasic.order.OrderServiceImpl;

public class AppConfig {

    // 이전에는 객체 참조변수에 어떤 객체를 생성할건지 직접 정해주었지만
    // 이제는 AppConfig에서 어레인지 해줄 것
    // AppConfig를 통해 Memberservice 갖다쓸 수 있도록
    // 필요한 구현객체는 AppConfig에서 다 설정한다.
    // 이제 생성한 객체 인스턴스의 참조를 "생성자를 통해서 주입" 해준다.
    // -> 객체의 생성과 연결을 AppConfig에서 한다.


    // 현재는 appconfig가 중복이 좀 있고, 설정정보임에도 불구하고 역할에 따른 구현이 잘 안보인다.
    // repository 분리
    // 아래처럼 메서드 명만 봐도 역할이 잘 분리된다.
    // 역할이 어떤 구현체를 갖는지도 삭 정리된다.
    // 이제 변경시 아래에 구현체만 갈아끼워주면 된다.
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    public MemberService memberService(){
        // MemberServiceImpl을 생성할때, 생성한 MemoryMemberRepository를 넘긴다.
        // MemberServiceImpl입장에서는 의존관계를 외부에서 주입해주는 것 같다고 해서 DI.
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }

}

