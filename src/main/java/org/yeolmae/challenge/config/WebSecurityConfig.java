package org.yeolmae.challenge.config;

//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.yeolmae.challenge.config.auth.PrincipalDetailsService;
import org.yeolmae.challenge.repository.MemberRepository;

import javax.sql.DataSource;

//
@Configuration
// @EnableWebSecurity : SpringSecurity FilterChain이 자동으로 포함
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)// secured, PreAuthorize/postAuthorize 어노테이션 활성화 //특정 경로에 접근할 수 있는 권한
//@RequiredArgsConstructor
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    //특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 권한에 따른 허용하는 url
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                .requestMatchers("/user/**").authenticated()//인증(로그인) 해야함.
                .requestMatchers("/admin/**").hasRole("ADMIN")//권한 있어야 함.
                .anyRequest().permitAll());//나머지 페이지들은 모두 권한 허용

        // login 설정
        http.formLogin((formLogin) -> formLogin
                .loginPage("/loginForm")
                .usernameParameter("email")
                .loginProcessingUrl("/login")// /login 주소가 호출되면 시큐리티가 낚아채서 대신 로그인 진행 // 로그인을 Spring Security가 대신 위임해서 처리해주는 기능(사용자 아이디(이메일), 비번 인증하는 것 대신 해줌.)
                .defaultSuccessUrl("/"));// "/" -> main 혹은 home 화면

        http.rememberMe((rememberMe) -> rememberMe
                .key("rememberMeKey")//인증받은 사용자 정보로 토큰 생성에 필요한 값
                .rememberMeParameter("remember-me")//html에서의 name 값
                .tokenValiditySeconds(24*60*60)
                .rememberMeServices(rememberMeServices(persistentTokenRepository())))
                .userDetailsService(new PrincipalDetailsService(memberRepository));//remember-me 토큰 유효시간 : 1일
//                .tokenRepository(persistentTokenRepository()));
//                .alwaysRemember(false)
//                .userDetailsService(userDetailsService));
//                .authenticationSuccessHandler(loginSuccessHandler()));//자동로그인 성공 시, 액션에 대해 정의해주는 handler

        // logout 설정
        http.logout((logout) -> logout
                .logoutSuccessUrl("/loginForm"));

        //csrf 비활성화
        http.csrf((csrf) -> csrf.disable());

        return http.build();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);

        tokenRepository.setCreateTableOnStartup(false);

        return tokenRepository;
    }

    @Bean
    public PersistentTokenBasedRememberMeServices rememberMeServices(PersistentTokenRepository tokenRepository){
        PersistentTokenBasedRememberMeServices rememberMeServices
                = new PersistentTokenBasedRememberMeServices("rememberMeKey", new PrincipalDetailsService(memberRepository), tokenRepository);
        rememberMeServices.setParameter("remember-me");
        rememberMeServices.setAlwaysRemember(false);

        return rememberMeServices;
    }

    // 비밀번호 암호화 - BCryptPasswordEncoder() 해시 함수 이용하여 암호화 처리
    @Bean // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    // css 나 js 파일 등의 정적 파일은 시큐리티 적용을 받을 필요 없이 무시하도록 함.
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//
//        return (web) -> web.ignoring()
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }
//

    // UserDetailsService 및 PasswordEncoder를 사용하여 사용자 아이디와 암호를 인증하는 AuthenticationProvider 구현
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
//
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
//
//        return daoAuthenticationProvider;
//    }

}