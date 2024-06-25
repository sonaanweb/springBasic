package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // 인터페이스의 다중 상속

    // 스프링 데이터 jpa가 자동으로 구현체 등록
    // JPOL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
