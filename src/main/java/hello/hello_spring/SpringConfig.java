package hello.hello_spring;

import hello.hello_spring.aop.TimeTraceAop;
import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// autowired 지우고 빈 직접 주입
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) { //스프링 컨테이너에서 등록한 걸 찾는다
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){ //로직 호출 - 스프링 빈에 등록
        return new MemberService(memberRepository);
    }

/*    @Bean
    public TimeTraceAop TimeTraceAop(){
        return new TimeTraceAop();
    }*/

// DB메모리
//    @Bean
//    public MemberRepository memberRepository(){
//      return new MemoryMemberRepository();
//      return new JdbcMemberRepository(dataSource);
//      return new JdbcTemplateMemberRepository(dataSource);
//      return new JpaMemberRepository(em);
}
