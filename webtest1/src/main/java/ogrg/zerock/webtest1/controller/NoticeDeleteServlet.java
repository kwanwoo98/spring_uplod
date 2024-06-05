package ogrg.zerock.webtest1.controller;

import ogrg.zerock.webtest1.dao.NoticeDAO;
import ogrg.zerock.webtest1.util.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/noticeDelete")
public class NoticeDeleteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 파라미터로 전달된 공지사항 번호를 가져옵니다.
        String noticeNo = request.getParameter("no");

        // 공지사항 번호가 유효한지 확인합니다.
        if (noticeNo == null || noticeNo.isEmpty()) {
            // 유효하지 않은 경우, 에러 페이지로 리다이렉트합니다.
            WebUtil.redirect(request, response, "/error.jsp", "Invalid notice number");
            return;
        }

        try {
            // 공지사항 번호를 이용하여 해당 공지사항을 삭제합니다.
            NoticeDAO noticeDAO = new NoticeDAO();
            noticeDAO.deleteNotice(Integer.parseInt(noticeNo));

            // 삭제가 완료되면 공지사항 목록 페이지로 리다이렉트합니다.
            WebUtil.redirect(request, response, "/noticelist");
        } catch (NumberFormatException e) {
            // 숫자 형식이 아닌 공지사항 번호가 전달된 경우, 에러 페이지로 리다이렉트합니다.
            WebUtil.redirect(request, response, "/error.jsp", "Invalid notice number format");
        }
    }
}