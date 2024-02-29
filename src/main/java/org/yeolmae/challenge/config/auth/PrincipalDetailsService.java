package org.yeolmae.challenge.config.auth;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yeolmae.challenge.domain.Member;
import org.yeolmae.challenge.repository.MemberRepository;

import java.util.Collections;
import java.util.Optional;

// 시큐리티 설정에서 loginProcessionUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
@Slf4j
//@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {


    @Autowired
    private MemberRepository memberRepository;

    // 로그인 구현 // email로 사용자 정보 조회
    // 시큐리티 session(내부 Authentication(내부 UserDetails))
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member memberEntity = memberRepository.findMemberByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("로그인 정보를 확인하세요."));

        UserDetails userDetails = User.builder()
                .username(memberEntity.getEmail())
                .password(memberEntity.getPw())
                .roles(memberEntity.getMemberRole().toString())
                .build();

        return userDetails;

//        log.info("🐱‍🚀 loadUserByUsername() 메소드 시작");
//
//        //Optional 객체가
//        Member memberEntity = memberRepository.findByEmail(email);
//        log.info("🐱‍🚀 찾아온 멤버 : {}",memberEntity);
//        if(memberEntity != null) {
//            return new User(
//                    memberEntity.getEmail(),
//                    memberEntity.getPw(),
//                    Collections.singleton(new SimpleGrantedAuthority(memberEntity.getMemberRole().name()))
//            );
//        }
//        log.info("🐱‍🚀 곧 에러를 발생 시킬 거임");
//        throw new NullPointerException(email + "는 데이터베이스에 없는 데이터입니다.");
    }

//    private UserDetails createUserDetails(Member member) {
//
//        String role = member.getMemberRole().value();
//        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role);
//
//        return new User(
//                member.getEmail(),
//                member.getPw(),
//                Collections.singleton(grantedAuthority)
//        );
//
//    }
}
