package org.zerock.b01.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.b01.dto.MemberDTO2;

@SpringBootTest
@Log4j2
public class Member2ServiceTests {
    @Autowired
    private MemberService2 memberService2;

    @Test
    public void testReadOne(){
        String memberID = "test4";
        MemberDTO2 memberDTO2 = memberService2.readOne(memberID);

        log.info("memberID : {}", memberDTO2.getMember_id());
        log.info("memberPW : {}", memberDTO2.getMember_pw());

    }
}
