package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    //생성자에 @Autowired 를 사용하면 객체 생성 시점에 스프링 컨테이너에서 해당 스프링 빈을 찾아서 주입 컨트롤러와 연결 @컴포넌트 스캔
    //스프링은 기본적으로 싱글톤(하나만 공유)등록
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm"; //createMemberForm.html
    }

    //action:method=post
    @PostMapping("/members/new")
    public String create(MemberForm form){ //MemberForm 내 setname을 통해 값이 들어감
        Member member = new Member();
        member.setName(form.getName()); //form에서 name 받아옴 get

        System.out.println("name = " + member.getName());

        memberService.join(member); //회원가입

        return  "redirect:/"; // 동작 끝난 후 홈화면 리턴
    }

    @GetMapping("/members")
    public String list(Model model){ //Model spring ui
        List<Member> members = memberService.findMembers(); //ctrl+alt+v 변수추출 findMembers() 전체목록
        model.addAttribute("members",members); //리스트 자체를 model에 담아 view에 넘김
        return "members/memberList";
    }
}
