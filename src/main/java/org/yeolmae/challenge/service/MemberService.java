//package org.yeolmae.challenge.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.yeolmae.challenge.domain.Member;
//import org.yeolmae.challenge.domain.dto.MemberJoinDTO;
//import org.yeolmae.challenge.repository.MemberRepository;
//
//@RequiredArgsConstructor
//@Service
//public class MemberService {
//
//    private final MemberRepository memberRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public int save(MemberJoinDTO dto) {
//
////        Member member = Member.builder()
////                .email(request.getEmail())
////                .pw(request.getPw())
////                .nickname(request.getNickname())
////                .build();
////
////        Member savedMember = memberRepository.save(member);
////
////        return savedMember.getId();
//
//        return memberRepository.save(Member.builder()
//                .email(dto.getEmail())
//                .pw(bCryptPasswordEncoder.encode(dto.getPw()))
//                .nickname(dto.getNickname())
//                .build()).getId();
//    }
//
//}
