package org.zerock.spbt.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.b01.domain.Member;


import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
//    @Query(value = "SELECT member_id,member_pw, name, phone, email1, email2, gender, agree, create_date from member2 where member_id = ?1 AND member_pw = ?2" , nativeQuery = true)
//    Member2 findByIdAndPassword(String member_id, String member_pw);

    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.mid = :mid and m.social = false ")
    Optional<Member> getWithRoles(String mid);

    @EntityGraph(attributePaths = "roleSet")
    Optional<Member> findByEmail(String email);
}
