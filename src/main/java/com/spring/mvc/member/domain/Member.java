package com.spring.mvc.member.domain;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    private String account; //계정명
    private String password; //비밀번호
    private String name; //사용자이름
    private String email; //이메일
    private Auth auth ;//Auth.ADMIN처럼 사용 //권한
    // 몇개만으로 정해져 있을 때 String으로 하면 안 좋음.. 잘못써도 에러가 안나기 때문에.. 타입을 클래스처럼 써줌

    private Date regDate; //가입일자

}