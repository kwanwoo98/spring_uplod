package ogrg.zerock.webtest1.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtil {
    public static void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + path);
    }

    public static void redirect(HttpServletRequest request, HttpServletResponse response, String path, String errorMessage) throws ServletException, IOException {
        // 에러 메시지를 request 속성에 저장합니다.
        request.setAttribute("errorMessage", errorMessage);
        // 에러 페이지로 포워딩합니다.
        request.getRequestDispatcher(path).forward(request, response);
    }
}