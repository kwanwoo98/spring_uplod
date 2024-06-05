package org.zerock.b01.service;

import org.zerock.b01.dto.MemberJoinDTO;

public interface MemberService {
    static class MidExistException extends Exception{

    }

    MemberJoinDTO getOne(String mid);

    void join(MemberJoinDTO memberJoinDTO) throws MidExistException;

    void modify(MemberJoinDTO memberJoinDTO);

    void remove(String mid);

    boolean isMember(MemberJoinDTO memberJoinDTO);
}
