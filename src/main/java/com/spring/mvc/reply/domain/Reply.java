package com.spring.mvc.reply.domain;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reply {

    private int replyNo; //댓글번호
    private int boardNo; //원본글번호
    private String replyText; //댓글내용
    private String replyWriter; //댓글작성자명
    private Date replyDate; //댓글작성시간


}
