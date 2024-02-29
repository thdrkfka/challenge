package org.yeolmae.challenge.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
//@ToString(exclude = "roleSet")
public class Member {
//
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(updatable = false, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String pw;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int level;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;


    //    @ElementCollection(fetch = FetchType.LAZY)
//    @Builder.Default
//    private Set<Level> level = new HashSet<>();

//    @ElementCollection(fetch = FetchType.LAZY)
//    @Builder.Default
//    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePassword(String pw) {
        this.pw = pw;
    }
    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }

}
