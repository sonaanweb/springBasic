package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // jpa는 엔티티매니저로 동작한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    // 저장 - jpa가 쿼리를 집어 넣는다
    @Override
    public Member save(Member member) {
        em.persist(member); //영구저장하다
        return member;
    }

    // 조회 pk
    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // 조회 타입, 식별자 pk값
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    
    // pk 기반이 아닌 것은 쿼리 작성
}