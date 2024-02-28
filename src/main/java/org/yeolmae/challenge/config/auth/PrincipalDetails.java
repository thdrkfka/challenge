package org.yeolmae.challenge.config.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.yeolmae.challenge.domain.Member;

import java.util.ArrayList;
import java.util.Collection;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인 진행
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어 줌.(Security ContextHolder)
// 오브젝트 => Authentication 타입 객체
// Authentication 안에 Member 정보가 있어야 됨.
// Member 오브젝트 타입 => UserDetails 타입 객체
// Security Session 영역(세션 정보 저장)
// => (session에 들어갈 수 있는 객체)Authentication
// => (Authentication 안에 들어있는 Member 정보 타입)UserDetails(=PrincipalDetails)
//
public class PrincipalDetails implements UserDetails {

    // Member 정보 // 컴포지션
    private Member member;

    public PrincipalDetails(Member member) { //PrincipalDetails의 생성자 만들어서 member 정보 넣어줌.
        this.member = member;
    }

    @Override // 해당 member의 권한 리턴하는 곳 //타입이 Collection 으로 정해져 있어서 member.getRole() 로 반환X => 타입 정해줌.
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collect = new ArrayList<>();

        collect.add(new GrantedAuthority() {
            @Override //String 리턴 가능
            public String getAuthority() {
                return member.getRole();
            }
        });

        return collect;
    }

    @Override
    public String getPassword() {
        return member.getPw();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override // 계정 만료 안 됨. => true
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // 계정 잠기지 않음. => true
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // 비밀 번호 만료 안됨. => true
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 계정 활성 안됨 => true
    public boolean isEnabled() {
        // 휴면계정 => 현재시간 - 로그인시간
        return true;
    }
}
