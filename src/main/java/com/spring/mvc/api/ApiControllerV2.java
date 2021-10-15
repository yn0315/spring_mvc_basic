package com.spring.mvc.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController//ResponseBody와 컨트롤러 합쳐진.. 바로 json으로 보내줌
@RequestMapping("/api/v2")
public class ApiControllerV2 {
    //지금까지 한 건 동기통신, 화면이 깜빡거림, 새로고침이 일어남, 동영상 보다가 댓글 쓰면 새로고침돼서 동영상이 중단됨..
    //비동기통신..컨텐트 요청은 그대로 있는 상태에서 댓글요청을 몰래 보내는 것..rest api 를 통해 보냄..
    //댓글을 목록에 넣으려면 json을 받아와서 넣어야함... 자바스크립트로..?

    /*
        # ResponseEntity
        -스프링 REST API가 응답할 때          Rest Api => 요청을 했을 때 json을 넘겨주는 것..
        응답데이터뿐만 아니라 응답상태코드,
        응답 헤더 등을 조작해서
        전송할 수 있게 해주는 객체

     */

    @GetMapping("/hello")
    public ResponseEntity<String> hello(String p) {
        if(p.equals("hi")) {
            return new ResponseEntity<String>("나도 안녕", HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("인사해줘", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
