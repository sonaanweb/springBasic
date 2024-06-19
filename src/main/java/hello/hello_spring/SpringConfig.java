package hello.hello_spring;

import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// autowired 지우고 빈 직접 주입
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){ //로직 호출 - 스프링 빈에 등록
        return new MemberService(memberRepository());
    }

    // DB메모리
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
