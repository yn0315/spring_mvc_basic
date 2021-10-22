package com.spring.mvc.member.controller;

import com.spring.mvc.member.domain.Member;
import com.spring.mvc.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
@Log4j2
@RequiredArgsConstructor
//@RestController//이렇게 쓰면 통째로 비동기.. 모바일로 많이 쓰면 이걸로 짜는게 좋음 , 웹이면 jsp방식으로 가는게 좋음,
//하이브리드면 섞어서 써줘야함
public class MemberController {

    private final MemberService memberService;
    //중복확인은 비동기로 보내야함..

    //회원가입 양식 요청
    @GetMapping("/member/sign-up")
    public String signUp(){
        //스프링시큐리티 라이브러리 넣으면 자동으로 락이 걸려서 게시판이고 뭐고 안 들어가지고 로그인창 열림
        return "member/join";
    }

    //아이디, 이메일 중복체크 비동기 요청 처리
    @GetMapping("/check")
    @ResponseBody//restcontroller 에서는 안 붙여도 됨, 클래스 자체가 비동기니까
    public ResponseEntity<Boolean> check(String type, String keyword) {
        log.info("/check 비동기요청 GET"+ type + "/" + keyword);
        return memberService.isDuplicate(type, keyword)
                ? new ResponseEntity<>(true, HttpStatus.OK)
                : new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //회원가입 등록처리
    @PostMapping("/member/sign-up")
    public String signUp(Member member) {
        log.info("/member/sign-up POST!-" + member);
        memberService.signUp(member);
        return "redirect:/board/list";
    }

    //로그인 양식 요청
    @GetMapping("/member/sign-in")
    public String signIn() {
        return "member/login";
    }

    //로그인 처리 요청
    @PostMapping("/loginCheck")
    public String loginCheck(String account, String password, Model model, HttpSession session) {

        log.info("/loginCheck POST!-" + account);

        String resultMessage = memberService.login(account, password);
        log.info(resultMessage);

        model.addAttribute("result", resultMessage);

        //로그인 성공시
        if(resultMessage.equals("loginSuccess")) {
            session.setAttribute("loginUser", memberService.getMember(account));//유저정보 안 날아가고 안전하게 세션으로..
            //로그인하면 유저정보를 jsp로 보내줌
            return "redirect:/board/list";//db를 안갔다옴
        }

        return "member/login-result";
    }

    //로그아웃처리
    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        log.info("/member/logout GET!");
        Member loginUser = (Member) session.getAttribute("loginUser");
        //세션에 담겨져 있던 로그인정보를 빼옴, object로 업캐스팅되어 리턴되므로 member로 다운캐스팅

        if(loginUser != null) {//로그인을 했다면
            session.removeAttribute("loginUser");
            session.invalidate();//세션 무효화
        }

        return "redirect:/board/list";
    }


}
