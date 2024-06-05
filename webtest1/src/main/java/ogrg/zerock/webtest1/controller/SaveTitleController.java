package ogrg.zerock.webtest1.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "saveTitleController", value = "/saveTitle")
@Log4j2
public class SaveTitleController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String title = req.getParameter("title").replaceAll(" ", "");
            //쿠키찾기
            Cookie viewTodoCookie = findCookie(req.getCookies(),"viewTodos");
            String todoListStr = viewTodoCookie.getValue();
            boolean exist = false;
            if(todoListStr != null && todoListStr.indexOf(title+"-")>=0){
                exist = true;
            }
            if(!exist){
                todoListStr += title+"-";
                viewTodoCookie.setValue(todoListStr);
                viewTodoCookie.setMaxAge(60*60*24);
                viewTodoCookie.setPath("/");
                resp.addCookie(viewTodoCookie);
            }
        } catch (Exception e) {
            log.error("An error occurred while saving the title", e);
            // 클라이언트에게 오류 응답 전송
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        resp.sendRedirect("/program");
    }
    private Cookie findCookie(Cookie[] cookies,String cookieName){
        Cookie targetCookie = null;
        if(cookies != null && cookies.length > 0){
            for(Cookie ck : cookies){
                if(ck.getName().equals(cookieName)){
                    targetCookie = ck;
                    break;
                }
            }
        }
        if(targetCookie == null){
            targetCookie = new Cookie(cookieName,"");
            targetCookie.setPath("/");
            targetCookie.setMaxAge(60*60*24);
        }
        return targetCookie;
    }
}