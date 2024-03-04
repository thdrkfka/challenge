package org.yeolmae.challenge.controller.restcontroller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.yeolmae.challenge.config.auth.PrincipalDetailsService;
import org.yeolmae.challenge.domain.Member;
import org.yeolmae.challenge.domain.MemberRole;
import org.yeolmae.challenge.repository.MemberRepository;
import org.springframework.stereotype.Controller;

@Controller
public class LoginJoinController {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PrincipalDetailsService principalDetailsService;

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
    public String join(@Valid Member member, @RequestParam(name = "adminRole", defaultValue = "false") boolean adminRole, Errors errors, Model model) {

        // 회원 객체 생성
//        Member member = new Member();
//        member.setEmail(email);
//        member.setPw(bCryptPasswordEncoder.encode(pw)); // 패스워드 암호화
//        member.setNickname(nickname);

        String rawPw = member.getPw();
        String encPw = bCryptPasswordEncoder.encode(rawPw);
        member.setPw(encPw);
        member.setMemberRole(adminRole ? MemberRole.ADMIN : MemberRole.USER);

        // 회원 저장
        memberRepository.save(member);

        return "redirect:/loginForm";
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
