package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    // 2. 기능
    Member save(Member member); //저장된 회원 반환
    Optional<Member> findById(Long id); //아이디로 회원 찾기
    Optional<Member> findByName(String name); //아이디로 이름 찾기

    //Optional: 없으면 null로 반환될 때

    List<Member> findAll(); //모든 회원 리스트 반환
}
