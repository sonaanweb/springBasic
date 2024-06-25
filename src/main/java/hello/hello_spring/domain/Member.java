package hello.hello_spring.domain;

import jakarta.persistence.*;

@Entity //jpa가 관리하는 엔티티
public class Member {

    // 1. 요구사항 vo 역할
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 알아서 생성
    private Long id; //시스템이 저장하는 id

    @Column(name = "name") // could not prepare statement DB 내 컬럼이름 오류 주의
    private String name;

    // alt+insert get/set
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
