package hello.hello_spring.domain;

public class Member {

    // 1. 요구사항 vo 역할
    private Long id; //시스템이 저장하는 id
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
