package com.pado.SpringCorePrincipleBasic.member;

/*
* 동시성 이슈가 발생할 수 있지만 그냥 넘어가자
* 동시성 문제가 걱정되면 콘커런트 해시맵을 쓰면 된다.
* */
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    @Override

    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
