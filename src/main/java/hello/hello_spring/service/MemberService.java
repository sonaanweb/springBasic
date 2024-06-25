package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    // 4. 회원 서비스 개발(비즈니스 로직)
    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // 외부에서 넣어주도록 설정
    public MemberService(MemberRepository memberRepository) { // alt+insert
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public long join(Member member) {

        long start = System.currentTimeMillis(); // 시간 측정

        // 같은 이름이 있는 중복 회원 X (ctrl+alt+v 변수추출)
        try {
            validateDuplicateMember(member); // ctrl+alt+shift+t = 메서드로 뽑아두기
            memberRepository.save(member); // 검증 후 저장
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{ //ifPresent(null이 아닌 값이 있으면 동작)=>Optional 기능
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        long start = System.currentTimeMillis(); // 시간 측정
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
