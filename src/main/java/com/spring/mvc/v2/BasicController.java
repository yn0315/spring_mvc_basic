package com.spring.mvc.v2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
     * 스프링 mvc 프레임워크의 컨트롤러는 웹 브라우저의
     * 요청을 받아 처리하며 응답할 화면(view)을 선택하는 역할을 합니다.
     *
     * 스프링이 이 클래스를 컨트롤러로 인식하게 하려면
     * @Controller 아노테이션을 붙여야 합니다.
     */

@Controller
public class BasicController {

    //요청처리 메서드

    //해당 경로로 url get 요청(주소창에 써서 들어오면 get...)이 오면 이 메서드가 처리하겠다. 이게 서블릿 하나의 역할...
   @GetMapping("/basic/test")
    public String test() {
       System.out.println("test요청이 들어왔어요!");
//        return "/WEB-INF/views/check.jsp"; //리턴값은 내가 이 요청이 끝난 후 응답할 페이지의 경로
        return "check";
    }

    //요청 url: /basic
    //basic.jsp가 열리도록 요청 메서드를 작성

    @GetMapping("/basic")
    public String test2() {
//       return "/WEB-INF/views/basic.jsp";
        return "basic";
    }


    //요청 파라미터(요청시에 브라우저에서 넘어오는 데이터) 받기
    //회원정보같은거, 게시판글번호 작성자같은..

    @GetMapping("/basic/join")
    public String join (HttpServletRequest request) {//요청을 받아야하니까 서블릿에서 썼던 방법처럼 request를 매개변수로 받음
        System.out.println("/basic/join 요청이 들어옴!");
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        int userAge = Integer.parseInt(request.getParameter("userAge"));


        System.out.println("userId = " + userId);
        System.out.println("userName = " + userName);
        System.out.println("userAge = " + userAge);

        return "";
    }

    @PostMapping("/basic/join2")
    public String join2 (@RequestParam("userId") String id, String userPw, String userName, int userAge,@RequestParam("hobbies") ArrayList<String> hobbies) {//요청을 받아야하니까 서블릿에서 썼던 방법처럼 request를 매개변수로 받음
        System.out.println("/basic/join2 요청이 들어옴!");

        System.out.println("userId = " + id);
        System.out.println("userName = " + userName);
        System.out.println("userAge = " + userAge);
        System.out.println("hobbies = " + hobbies);
        return "";
    }

    @PostMapping("/basic/join3")
    public String join(User user, Model model) {
        System.out.println("아이디: " + user.getUserId());
        System.out.println("이름: " + user.getUserName());
        System.out.println("취미: " + user.getHobbies());

        model.addAttribute("user", user);

       return "request/result";
    }


    //화면쪽으로 서버의 데이터를 전달하는 방법
    @GetMapping("/model")//Model은 데이터를 담아서 전달하기 위해 사용하는 봉다리같은거........
    public String modelBasic(Model model, int age){
       //클라이언트 쪽으로 데이터를 greet이라는 이름으로 메롱을 담아서 전달
       model.addAttribute("greet", "메롱");
       model.addAttribute("myAge", age);

       int birthYear = LocalDate.now().getYear() -age +1;
       model.addAttribute("birth", birthYear);
       return "request/model_study";

    }

    @GetMapping("/res-quiz")//주소창에 쓰는 거
    public String quiz() {
       return "request/res_quiz";//jsp 파일경로
    }


    @PostMapping("/response/quiz")
    public String login(String userId, String userPw, Model model) {
        System.out.println("로그인 요청 들어옴!");
        model.addAttribute("account", userId);
       if(userId.equals("kim123") && userPw.equals("kkk1234")) {
           return  "request/success";
       } else {
           return "request/fail";
       }

    }


}
