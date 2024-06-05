package org.zerock.spbt.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Member;
import org.zerock.b01.repository.MemberRepository;
import org.zerock.b01.security.dto.MemberSecurityDTO;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername : " + username);
        Optional<Member> result = memberRepository.getWithRoles(username);
        if (result.isEmpty()) { // 해당 아이디를 가진 사용자가 없으면
            throw new UsernameNotFoundException("username not found..");
        }
        // 데이터가 있으면 멤버 데이터 저장
        Member member = result.get();
        // Member 객체를 MemberSecurityDTO 객체로 반환
        MemberSecurityDTO memberSecurityDTO = new MemberSecurityDTO(
                member.getMid(),
                member.getMpw(),
                member.getEmail(),
                member.isDel(), false,
                // ROLE_USER 또는 ROLE_ADMIN 출력
                member.getRoleSet().stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                        .collect(Collectors.toList())
        );

        log.info("memberSecurityDTO");
        log.info(memberSecurityDTO);

        return memberSecurityDTO;
    }

    //    private PasswordEncoder passwordEncoder;
//
//    public CustomUserDetailsService() {
//        this.passwordEncoder = new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("loadUserByUsername : " + username);
//
//        UserDetails userDetails = User.builder()
//                .username(username)
//                //.password("1111")
//                .password(passwordEncoder.encode("1111"))
//                .authorities("ROLE_USER")
//                .build();
//        return userDetails;
//    }
}
