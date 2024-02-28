package org.yeolmae.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yeolmae.challenge.domain.Member;

//import java.util.Optional;

//CRUD 함수 사용
public interface MemberRepository extends JpaRepository<Member, Integer> {

    // select * from member where email = ?
    public Member findByEmail(String email); //Jpa Query method

//    // Opitonal<Member> : Member 객체를 감싼 옵셔널 객체를 반환하는 메서드, 데이터베이스에서 특정 조건에 맞는 데이터 조회하는 메서드
//    // email로 사용자 정보 가져옴.
//    Optional<Member> findMemberByEmail(String email);
//
}
