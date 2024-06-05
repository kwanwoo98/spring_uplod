package org.zerock.b01.repsitory;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.domain.Member2;
import org.zerock.b01.repository.MemberRepository;
import org.zerock.b01.repository.MemberRepository2;

@SpringBootTest
@Log4j2
public class Member2RepositoryTests {
    @Autowired
    private MemberRepository2 memberRepository2;

    @Test
    public void testInsert(){
        Member2 member2 = Member2.builder()
                .member_id("test3")
                .member_pw("1234")
                .name("testuser1")
                .phone("010-1234-5678")
                .email1("test")
                .email2("naver.com")
                .gender("male")
                .agree(true)
                .build();

        Member2 result = memberRepository2.save(member2);
    }
}
