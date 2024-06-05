package org.zerock.b01.service;


import org.zerock.b01.dto.MemberDTO2;

public interface MemberService2 {
    String register(MemberDTO2 memberDTO2);
    MemberDTO2 readOne(String memberId);
    MemberDTO2 login(String member_id, String member_pw);

}
