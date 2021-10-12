package com.spring.mvc.common.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Page {

    private int pageNum; //페이지 번호
    private int amount; //한 페이지당 게시물 수

//    기본생성자를 부른 다음 setter를 씀....
    public Page() {
//  자동으로 1페이지를 보여주도록..
    this.pageNum = 1;
    this.amount = 10;
    }

    public Page(int pageNum) {
        if(pageNum <=0) {
            this.pageNum = 1;
            return;//안해놓으면 이상한 페이지 요청한 것이 실행됨
        }
        this.pageNum = pageNum;
    }

    public Page(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
}
