package ogrg.zerock.webtest1.controller;


import lombok.extern.log4j.Log4j2;
import ogrg.zerock.webtest1.dto.MemberDTO;
import ogrg.zerock.webtest1.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "loginController", value = "/login")
@Log4j2
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Login Get ...........");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Login Post ...........");
        String mid = req.getParameter("id");
        String mpw = req.getParameter("pw");

        String str = mid+mpw;

        HttpSession session = req.getSession();

        session.setAttribute("loginInfo", str);

        resp.sendRedirect("/");

    }
}
