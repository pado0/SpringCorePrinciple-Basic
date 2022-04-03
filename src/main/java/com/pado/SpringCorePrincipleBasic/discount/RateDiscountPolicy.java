package com.pado.SpringCorePrincipleBasic.discount;

import com.pado.SpringCorePrincipleBasic.member.Grade;
import com.pado.SpringCorePrincipleBasic.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discoutnPercent = 20;
    /**
     * @return 할인금액
     */
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discoutnPercent / 100; // 이거 진짜 별로다..
        }else return 0;
    }
}
