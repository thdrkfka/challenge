package org.yeolmae.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

}

