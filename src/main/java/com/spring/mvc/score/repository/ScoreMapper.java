package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper//마이바티스 SQL처리 인터페이스
public interface ScoreMapper {

    //점수저장기능
    void save(Score score);

    //전체점수 정보 조회기능
    List<Score> findAll();

    //개별 점수 정보 조회기능
    Score findOne(int stuNum);

    //점수 정보 삭제 기능
    void remove(int stuNum);


}
