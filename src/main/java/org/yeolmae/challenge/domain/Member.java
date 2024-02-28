package org.yeolmae.challenge.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "roleSet")
public class Member {
//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(updatable = false, unique = true)
    private String email;
    private String pw;
    private String nickname;
    private String role;

    //    @ElementCollection(fetch = FetchType.LAZY)
//    @Builder.Default
//    private Set<Level> level = new HashSet<>();
//
    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();
    public void changePassword(String pw) {
        this.pw = pw;
    }
    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

}
