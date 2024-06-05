package org.zerock.b01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.zerock.b01.domain.Member2;

public interface MemberRepository2 extends JpaRepository<Member2, String> {
    @Query(value = "SELECT member_id,member_pw, name, phone, email1, email2, gender, agree, create_date from member2 where member_id = ?1 AND member_pw = ?2" , nativeQuery = true)
    Member2 findByIdAndPassword(String member_id, String member_pw);
}
