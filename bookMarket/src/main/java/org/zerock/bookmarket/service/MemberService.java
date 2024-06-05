package org.zerock.bookmarket.service;

import org.zerock.bookmarket.domain.MemberVO;
import org.zerock.bookmarket.dto.MemberDTO;
import org.zerock.bookmarket.mapper.MemberMapper;

public interface MemberService {
    void register(MemberDTO memberDTO);
    MemberDTO login(MemberDTO memberDTO)  throws Exception;
}
