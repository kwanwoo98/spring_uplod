package org.zerock.b01.repsitory;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.b01.domain.Member;
import org.zerock.b01.domain.MemberRole;
import org.zerock.b01.repository.MemberRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void  insertMember(){
        // 1부터 100까지 반복하기위한 스트림
        IntStream.rangeClosed(1,100).forEach(i ->{
            // 회원 생성 (비밀번호는 passwordEncoder 이용해서 1111로 인코딩)
            Member member = Member.builder()
                    .mid("member"+i)
                    .mpw(passwordEncoder.encode("1111"))
                    .email("email"+i+"@aaa.bbb")
                    .build();
            // 회원 권한 설정(일반회원)
            member.addRole(MemberRole.USER);
            // 관리자 권한 설정
            if(i>=90){
                member.addRole(MemberRole.ADMIN);
            }
            // 데이터베이스에 저장
            memberRepository.save(member);
        });
    }

    @Test
    public void testRead(){
        Optional<Member> result = memberRepository.getWithRoles("member100");
        Member member = result.orElseThrow();

        log.info(member);
        log.info(member.getRoleSet());

        member.getRoleSet().forEach(memberRole -> log.info(memberRole.name()));
    }
}
