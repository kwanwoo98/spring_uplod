package ogrg.zerock.webtest1.controller;

import ogrg.zerock.webtest1.dao.MemberDAO;
import ogrg.zerock.webtest1.dto.MemberDTO;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "joinController", value = "/join")
public class JoinController extends HttpServlet {

    private Logger log;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        log.info("Login Get ...........");
        req.getRequestDispatcher("/join.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 회원 정보를 받아와서 DTO 객체에 저장
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(request.getParameter("memberId"));
        memberDTO.setEmail1(request.getParameter("memberId"));
        memberDTO.setEmail2(request.getParameter("email2"));
        memberDTO.setName(request.getParameter("name"));
        memberDTO.setMemberPw(request.getParameter("memberPw"));
        memberDTO.setPhone(request.getParameter("phone"));
        memberDTO.setGender(request.getParameter("gender"));
        memberDTO.setAgree(request.getParameter("agree") != null); // 동의 여부 확인
        // 가입일은 현재 날짜로 설정
        memberDTO.setCreateDate(LocalDate.now());

        // MemberDAO를 사용하여 회원 정보를 DB에 저장
        MemberDAO memberDAO = new MemberDAO();
        memberDAO.insertMember(memberDTO);

        // 회원가입 후 로그인 페이지로 이동
        response.sendRedirect("/");
    }
}
