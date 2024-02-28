//package org.yeolmae.challenge.controller;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.yeolmae.challenge.domain.dto.MemberJoinDTO;
//import org.yeolmae.challenge.service.MemberService;
//
//@Controller
//@RequiredArgsConstructor
//public class MemberJoinController {
//
//    private final MemberService memberService;
//
//
//    @PostMapping("/user")
//    public String signup(MemberJoinDTO dto) {
//
//        memberService.save(dto);
//
//        return "redirect:/login";
//    }
//
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
//}
