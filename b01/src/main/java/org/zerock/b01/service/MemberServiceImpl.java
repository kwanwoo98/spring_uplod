package org.zerock.b01.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.b01.domain.Member;
import org.zerock.b01.domain.MemberRole;
import org.zerock.b01.dto.MemberJoinDTO;
import org.zerock.b01.repository.MemberRepository;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public MemberJoinDTO getOne(String mid){
        return modelMapper.map(memberRepository.findById(mid), MemberJoinDTO.class);
    }

    @Override
    public void join(MemberJoinDTO memberJoinDTO) throws MidExistException {
        String mid = memberJoinDTO.getMid();
        boolean exist = memberRepository.existsById(mid);
        if(exist) {
            throw new MidExistException();
        }
        Member member = modelMapper.map(memberJoinDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberJoinDTO.getMpw()));
        member.addRole(MemberRole.USER);

        log.info("---------------");
        log.info(member);
        log.info(member.getRoleSet());

        memberRepository.save(member);
    }

    @Override
    public void modify(MemberJoinDTO memberJoinDTO){
        memberRepository.save(modelMapper.map(memberJoinDTO, Member.class));

    }

    @Override
    public void remove(String mid) {
        memberRepository.deleteById(mid);
    }

    @Override
    public boolean isMember(MemberJoinDTO memberJoinDTO) {
        String mid = memberJoinDTO.getMid();
        boolean exist = memberRepository.existsById(mid);
        return exist;
    }
}
