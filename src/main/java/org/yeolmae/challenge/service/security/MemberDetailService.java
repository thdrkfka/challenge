//package org.yeolmae.challenge.service.security;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.yeolmae.challenge.repository.MemberRepository;
//
//@Service
//@RequiredArgsConstructor
//public class MemberDetailService implements UserDetailsService {
//
//    private final MemberRepository memberRepository;
//
//    //사용자 정보(email) 가져옴
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
//        return memberRepository.findMemberByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("Member not found with email: " + email));
//    }
//
//}
