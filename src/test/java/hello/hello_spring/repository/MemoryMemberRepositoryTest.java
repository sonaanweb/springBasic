package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    // [코드 구현 후 테스트 케이스 작성]

    // MemberRepository -> MemoryMemberRepository(test) 수정
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메서드가 끝날 때마다 동작! 테스트 케이스는 메서드 순서가 보장되지 않으므로 오류가 생길 수 있다. 그러므로 메서드 하나가 끝날 때마다 clear코드 추가
    public void afterEach(){
        repository.clearStore();
    }

    // Test insert run
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member); // 저장 (id 세팅(시퀀스++))
        Member result = repository.findById(member.getId()).get(); // get으로 바로 꺼내는 건 좋은 방법X 하지만 test라 get
        
        // 검증방법
        // System.out.println("result = " + (result == member));
        // jupiter-> Assertions.assertEquals(member, result); //member가 find했을 때 result 값과 같은가 == 녹색불(true)
        // org.assertj.core.api-> (alt+enter -> import api) 앞에 Assertions. 생략가능
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // rename shift+f6
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        //        || "spring1" == member1 (true)
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
