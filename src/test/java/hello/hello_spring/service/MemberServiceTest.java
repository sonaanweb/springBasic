package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    // 테스트는 한글로 적어도 무방(빌드 시 코드에 포함되지 않음)
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach // 같은 memoryRepository 사용
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // Clear코드
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given 주어진 상황
        Member member = new Member();
        member.setName("hello");

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
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // 메세지 검증
        // () -> memberService.join(member2)) 1. 로직 실행 |
        // assertThrows(IllegalStateException.class 2. 예외 발생해야 함
        
/* try catch문 방법

        try {
            memberService.join(member2);
            fail("예외가 발생해야 합니다");
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); //service 예외 메세지와 같아야 함 검증
        }
*/
        // then
    }

    @Test

    void findMembers() {
    }

    @Test
    void findOne() {
    }
}