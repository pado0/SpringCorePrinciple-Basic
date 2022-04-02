package com.pado.SpringCorePrincipleBasic.discount;

import com.pado.SpringCorePrincipleBasic.member.Member;

public interface DiscountPolicy {

    /*
    * @return 할인 대상 금액, 얼마를 할인해줄지 리턴
    * */
    int discount(Member member, int price);
}
