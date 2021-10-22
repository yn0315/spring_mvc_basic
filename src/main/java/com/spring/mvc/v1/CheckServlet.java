package com.spring.mvc.v1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/check123")//check로 들어오는 거 받는다
public class CheckServlet extends HelloServlet{

    @Override              //요청정보req로 들어감
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("나이 확인중...");

        //요청시 전달된 form데이터를 읽어줌
        String ageStr = req.getParameter("age");
        System.out.println("age = " + ageStr);

        int age = Integer.parseInt(ageStr);

        String msg = (age > 19)? "성인" : "미성년자";

        System.out.println("msg = " + msg);

        //옛날 기술
        //HTML 렌더링
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        //html을 그릴 펜
        PrintWriter w = resp.getWriter();
        w.println("<html>");
        w.println(" <head>");
        w.println(" </head>");
        w.println(" <body>");

        for (int i = 0; i < 3; i++) {

            w.println(" <h1>당신은"+ msg+ "입니다.</h1>");
        }

        w.println(" </body>");
        w.println("</html>");
    }
}
