package com.spring.mvc.score.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Score {

    private int stuNum; //학번
    private String name; //이름
    private int kor; //국어점수
    private int eng; //영어점수
    private int math; //수학점수
    private int total; //총점
    private double average; //평균

    //순차적 학번 부여 정적필드
    private static int seq;//학번 공유해서 누적시켜야 하니까

    public Score() {
        this.stuNum = ++seq;//1번부터 시작해야하니까
        this.total = this.kor + this.eng + this.math;
        this.average = Math.round((this.total / 3.0)*100) / 100.0;//long(실수) / double로 해야하니까 100.0으로
    }

    public Score(String name, int kor, int eng, int math) {
        this();
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }
}
