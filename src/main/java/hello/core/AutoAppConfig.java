package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 모드 끌어올린 Bean중에서 뺄거 지정
        // Configuration도 Bean에 등록되기 때문에 충돌이 발생 할 수 있다.
        // 그래서 Configuration은 뺀다.
        // 그리고 예제 코드를 살리기 위해서.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}