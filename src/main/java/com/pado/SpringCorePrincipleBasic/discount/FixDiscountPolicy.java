package com.pado.SpringCorePrincipleBasic.discount;

import com.pado.SpringCorePrincipleBasic.member.Grade;
import com.pado.SpringCorePrincipleBasic.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){ // enum은 ==으로 비교
            return discountFixAmount;
        }else return 0;  // vip가 아니면 리턴 0원
    }
}
