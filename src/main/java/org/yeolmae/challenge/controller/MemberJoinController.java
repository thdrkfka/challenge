package org.yeolmae.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.yeolmae.challenge.domain.Member;
import org.yeolmae.challenge.domain.MemberRole;
import org.yeolmae.challenge.repository.MemberRepository;
import org.springframework.stereotype.Controller;

@Controller
public class MemberJoinController {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
    public String join(Member member, @RequestParam(name = "adminRole", defaultValue = "false") boolean adminRole) {
        System.out.println(member);
//        member.setRole("ROLE_USER");
        memberRepository.save(member); //회원가입 됨. 비밀번호 :1234 => 시큐리티로 로그인 할 수 없음. 이유? 패스워드 암호화X
        // 패스워드 암호화
        String rawPw = member.getPw();
        String encPw = bCryptPasswordEncoder.encode(rawPw);
        member.setPw(encPw);

        member.setMemberRole(adminRole ? MemberRole.ADMIN:MemberRole.USER);
        memberRepository.save(member);
        return "redirect:/loginForm";//정상적으로 회원가입이 이뤄지면 redirect 함.
    }

//    @GetMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response) {
//
//        new SecurityContextLogoutHandler()
//                .logout(request, response, SecurityContextHolder.getContext().getAuthentication());
//
//        return "redirect:/login";
//
//    }
//
//
}
