package org.zerock.b01.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Member2;
import org.zerock.b01.dto.MemberDTO2;
import org.zerock.b01.repository.MemberRepository2;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class MemberService2Impl implements MemberService2 {
    private final ModelMapper modelMapper;
    private final MemberRepository2 memberRepository2;

    @Override
    public String register(MemberDTO2 memberDTO2) {
        Member2 member2 = modelMapper.map(memberDTO2, Member2.class);
        Member2 member2ID = memberRepository2.save(member2);

        return member2.getMember_id();
    }

    @Override
    public MemberDTO2 readOne(String memberId) {
        Optional<Member2> result = memberRepository2.findById(memberId);
        Member2 member2 = result.orElseThrow();
        MemberDTO2 memberDTO2 = modelMapper.map(member2, MemberDTO2.class);
        return memberDTO2;
    }

    @Override
    public MemberDTO2 login(String member_id, String member_pw) {
        Member2 member2 = memberRepository2.findByIdAndPassword(member_id, member_pw);
        return modelMapper.map(member2, MemberDTO2.class);
    }
}
