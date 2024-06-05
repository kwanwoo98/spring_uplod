package ogrg.zerock.webtest1.service;

import lombok.extern.log4j.Log4j2;
import ogrg.zerock.webtest1.dao.MemberDAO;
import ogrg.zerock.webtest1.dto.MemberDTO;

@Log4j2
public enum MemberService {
    INSTANCE;
    private MemberDAO dao = new MemberDAO();

    public void addMember(MemberDTO dto) {
        dao.insertMember(dto);
    }

    MemberService(){
        dao = new MemberDAO();

    }
//
//    public MemberDTO login(String mid, String mpw) throws Exception{
//        MemberVO vo = dao.getWithPassword(mid,mpw);
//        MemberDTO dto = modelMapper.map(vo, MemberDTO.class);
//        return dto;
//    }
//
//    public void updateUuid(String mid, String uuid) throws Exception{
//        dao.updateUuid(mid,uuid);
//    }
//
//    public MemberDTO getByUUID(String uuid) throws Exception{
//        MemberVO vo = dao.selectUUID(uuid);
//        MemberDTO dto = modelMapper.map(vo, MemberDTO.class);
//        return dto;
//    }
}








