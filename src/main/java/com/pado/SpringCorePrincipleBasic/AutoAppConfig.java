package com.pado.SpringCorePrincipleBasic;

import com.pado.SpringCorePrincipleBasic.member.MemberRepository;
import com.pado.SpringCorePrincipleBasic.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
// @Component 어노테이션이 붙은 클래스를 찾아 다 스프링 빈으로 등록해줌.
@ComponentScan(
        // 제외할 것을 설정. Appconfig.java 등 설정정보 넣어놨던 파일을 제외하기 위해 설정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)

// @Bean으로 등록해준게 없다.
public class AutoAppConfig {

    //@Bean// (name = "memoryMemberRepository") // -> 자동생성 빈 vs 수동 생성 빈 우선순위 비교용
    public MemberRepository memoryRepository(){
        return new MemoryMemberRepository();
    }
}
