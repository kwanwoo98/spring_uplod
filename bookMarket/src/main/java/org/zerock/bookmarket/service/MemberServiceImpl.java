package org.zerock.bookmarket.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.bookmarket.domain.MemberVO;
import org.zerock.bookmarket.dto.MemberDTO;
import org.zerock.bookmarket.mapper.MemberMapper;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(MemberDTO memberDTO) {
        log.info(modelMapper);
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        log.info(memberVO);
        memberMapper.insert(memberVO);
    }

    @Override
    public MemberDTO login(MemberDTO memberDTO) throws Exception{
        MemberVO memberVO = memberMapper.login(modelMapper.map(memberDTO,MemberVO.class));
        return modelMapper.map(memberVO, MemberDTO.class);
    }

}
