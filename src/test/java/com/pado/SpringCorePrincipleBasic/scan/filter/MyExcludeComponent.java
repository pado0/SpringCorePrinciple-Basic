package com.pado.SpringCorePrincipleBasic.scan.filter;

import java.lang.annotation.*;

// 사용자정의 어노테이션을 두개 만들어 컴포넌트 스캔에서 제외할 것
@Target(ElementType.TYPE) // Type은 클래스 레벨에 붙는 것
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
