package org.yeolmae.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yeolmae.challenge.domain.Member;
import org.yeolmae.challenge.repository.MemberRepository;

@Controller // View를 리턴
public class MemberViewController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"","/"})
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping("/admin")
    public @ResponseBody String admin() {
        return "admin";
    }

    // 스프링 시큐리티가 낚아챘음 -> websecurityconfig 생성 후, 작동 안함
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    // 회원 가입창
    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    // 실제로 회원 가입 이뤄짐
    @PostMapping("/join")
    public String join(Member member) {
        System.out.println(member);
        member.setRole("ROLE_USER");
        memberRepository.save(member); //회원가입 됨. 비밀번호 :1234 => 시큐리티로 로그인 할 수 없음. 이유? 패스워드 암호화X
        // 패스워드 암호화
        String rawPw = member.getPw();
        String encPw = bCryptPasswordEncoder.encode(rawPw);
        member.setPw(encPw);
        memberRepository.save(member);
        return "redirect:/loginForm";//정상적으로 회원가입이 이뤄지면 redirect 함.
    }

    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")// 여러 권한 주고 싶을 때 사용
    @GetMapping("/info")
    public @ResponseBody String info() {
        return "개인 정보 프로필";
    }

    @Secured("ROLE_ADMIN") //특정경로 권한
    @GetMapping("/challenge/register")
    public @ResponseBody String registerChallenge() {
        return "챌린지 등록 페이지";
    }


//    @GetMapping("/home")
//    public String home() {
//        return "home";
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @GetMapping("/signup")
//    public String signup() {
//        return "signup";
//    }

}

