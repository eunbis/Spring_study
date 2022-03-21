package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration //설정정보이므로 추가
@ComponentScan( //컴포넌트 스캔
//        basePackages = "hello.core.member", //탐색 시작 패키지 위치 지정
//        basePackageClasses = AutoAppConfig.class, //탐색 시작 클래스 위치 지정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}