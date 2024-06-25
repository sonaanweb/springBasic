package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 테스트(통합 테스트). 스프링 컨테이너와 테스트를 함께 실행한다
@Transactional // 테스트 케이스에 있으면! 테스트 실행 시 트랜잭션 먼저 실행 - DB 데이터 넣은 후 -> 테스트 끝나면 롤백!(메서드 하나하나 적용)
class MemberServiceIntergrationTest {

    // 테스트는 한글로 적어도 무방(빌드 시 코드에 포함되지 않음) + Autowired 편한 대로 써도 됨 (테스트)
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository; // 구현체는 스프링이 config 한 곳에서 올라옴

/*    @AfterEach // Clear코드
    public void afterEach(){
        memberRepository.clearStore();
    }*/

    @Test
    //@Commit DB반영 - 눈으로 보임
    void 회원가입() {
        //given 주어진 상황
        Member member = new Member();
        member.setName("spring1");

        //when 실행했을 때
        Long saveId = memberService.join(member);

        //then 결과
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring2");

        Member member2 = new Member();
        member2.setName("spring2");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 메세지 검증
        // () -> memberService.join(member2)) 1. 로직 실행 |
        // assertThrows(IllegalStateException.class 2. 예외 발생해야 함

    }
}