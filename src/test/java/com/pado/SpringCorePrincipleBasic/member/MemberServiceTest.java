package com.pado.SpringCorePrincipleBasic.member;
import com.pado.SpringCorePrincipleBasic.AppConfig;
import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    //MemberService memberService = new MemberServiceImpl();
    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    @Test
    void join() {

        //given : 이게 주어졌을때
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when : 이러면
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then : 이렇게 된다
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
