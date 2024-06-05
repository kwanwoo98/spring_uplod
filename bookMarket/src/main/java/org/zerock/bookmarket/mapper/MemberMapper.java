package org.zerock.bookmarket.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.bookmarket.domain.MemberVO;

import java.util.List;

public interface MemberMapper {
    void insert(MemberVO memberVO);
    MemberVO login(MemberVO memberVO);
}
