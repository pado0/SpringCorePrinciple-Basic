package com.pado.SpringCorePrincipleBasic.discount;

import com.pado.SpringCorePrincipleBasic.annotation.MainDiscountPolicy;
import com.pado.SpringCorePrincipleBasic.member.Grade;
import com.pado.SpringCorePrincipleBasic.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
@Primary
//@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 20;
    /**
     * @return 할인금액
     */
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100; // 이거 진짜 별로다..
        }else return 0;
    }
}
