package com.spring.mvc.api;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.service.BoardService;
import com.spring.mvc.common.paging.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1")
public class ApiControllerV1 {

    @GetMapping("/hello")
    @ResponseBody //클라이언트에게 순수데이터 리턴, 이거 안붙이면 서버사이드렌더링됨
    public String hello() {
        //여태까지 했던건 jsp에서 서버사이드렌더링을 통해 순수한html로 변함....${}같은 걸 변환

        return "안녕";//순수하게 안녕이라는 데이터를 보냄, 브라우저에 404 말고 안녕 뜸!!! 오!!!
    }

    @GetMapping("/hobby")
    @ResponseBody
    public String[] hobby() {
        return new String[]{"음악감상", "축구", "꽃꽂이"};//자바언어의 배열을 json형식으로 리턴, (자바스크립트형식..) []
    }

    @GetMapping("/major")
    @ResponseBody
    public List<String> major() {
        return Arrays.asList("정보보안", "컴퓨터공학", "수학과", "성악과");
    }

    @GetMapping("/article")
    @ResponseBody
    public Board board() {
        Board board = new Board();
        board.setBoardNo(600);
        board.setTitle("JSON제목");
        board.setContent("테스트중");
        board.setWriter("김제이슨");
        return board;//{}형태로 리턴
    }

    @GetMapping("/food")
    @ResponseBody
    public Map<String, String> food() {
        Map<String, String> foods= new HashMap<>();
        foods.put("한식", "육개장");
        foods.put("양식", "파스타");
        foods.put("중식", "탕수육");
        return foods;
    }

    @Autowired
    private BoardService boardService;

    @GetMapping("/b_list")
    @ResponseBody
    public List<Board> list() {
        return boardService.getArticles(new Page(1,20));//[{},{}]형태
    }

}
