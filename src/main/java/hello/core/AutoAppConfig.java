package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // basePackages는 스캔을 시작할 위치를 지정, 여러개도 지정 가능.
        // 지정하지 않으면 ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다
//        basePackages = "hello.core.member",
        // 모드 끌어올린 Bean중에서 뺄거 지정
        // Configuration도 Bean에 등록되기 때문에 충돌이 발생 할 수 있다.
        // 그래서 Configuration은 뺀다.
        // 그리고 예제 코드를 살리기 위해서.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}

