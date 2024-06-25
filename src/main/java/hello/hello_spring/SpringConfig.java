package hello.hello_spring;

import hello.hello_spring.repository.*;
import hello.hello_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// autowired 지우고 빈 직접 주입
@Configuration
public class SpringConfig {

    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(){ //로직 호출 - 스프링 빈에 등록
        return new MemberService(memberRepository());
    }

    // DB메모리
    @Bean
    public MemberRepository memberRepository(){
//      return new MemoryMemberRepository();
//      return new JdbcMemberRepository(dataSource);
//      return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
