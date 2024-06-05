package ogrg.zerock.webtest1.controller;

import lombok.extern.log4j.Log4j2;
import ogrg.zerock.webtest1.dao.MemberDAO;
import ogrg.zerock.webtest1.dao.NoticeDAO;
import ogrg.zerock.webtest1.dto.NoticeDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/noticeadd")
@Log4j2
public class NoticeAdd extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("noticeadd Get ...........");
        req.getRequestDispatcher("/notice_add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("noticeadd Post ...........");
        NoticeDTO noticeDTO = new NoticeDTO();

        noticeDTO.setTitle(req.getParameter("title"));
        noticeDTO.setContent(req.getParameter("contents"));

        NoticeDAO noticeDAO = new NoticeDAO();
        noticeDAO.insertNotice(noticeDTO);

        resp.sendRedirect("/noticelist");
    }
}