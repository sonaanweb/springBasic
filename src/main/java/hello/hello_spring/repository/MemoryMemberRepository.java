package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // 3.구현체 implements MemberRepo alt+enter -> insert method all

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);     // set id할 때 시퀀스 값 +1
        store.put(member.getId(), member); // 아이디 저장 후 store에 저장 map에 넣어줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // store에서 꺼내옴
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) // 람다; 파라미터에 넘어온 name과 같은 지 확인 필터링
                .findAny(); // == filter 돌려 찾으면 Optional에 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // test 코드 메서드
    public void clearStore(){
        store.clear();
    }
}