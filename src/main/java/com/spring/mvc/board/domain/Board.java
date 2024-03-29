package com.spring.mvc.board.domain;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor//기본생성자
public class Board {

    //domain, repository, controller, service -기본구조

    private int boardNo; //글번호
    private String writer; //작성자
    private String title; //글제목
    private String content; //글내용
    private int viewCnt; //조회수
    private Date regDate; //글작성시간

    private boolean newFlag; //신규게시물 여부 //boolean 은 getter 사용할 때 is를 붙임
    private boolean hitFlag; //조회수 10이 넘어가는지 여부



}
