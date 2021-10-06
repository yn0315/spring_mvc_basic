package com.spring.mvc.score.service;

import com.spring.mvc.score.domain.Score;
import com.spring.mvc.score.repository.ScoreMapper;
import com.spring.mvc.score.repository.ScoreRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//컨트롤러와 저장소 중간에서 데이터 중간처리를 담당하는 클래스
@Log4j2
@Service//컴포넌트와 동일하지만 따로 만들어져 있음..

public class ScoreService {

    private final ScoreRepository scoreRepository;//컨트롤러와 저장소 중간에서 저장소에 보내는 역할이기 때문에..........
    private final ScoreMapper scoreMapper;//알트 엔터 생성자 매개변수 추가

    @Autowired
    public ScoreService(@Qualifier("jr") ScoreRepository scoreRepository, ScoreMapper scoreMapper) {
        this.scoreRepository = scoreRepository;
        this.scoreMapper = scoreMapper;
    }

    //점수정보 저장 전에 해야할 처리
    public void register(Score score) {
        score.calcTotal();
//        scoreRepository.save(score);
        scoreMapper.save(score);

    }




}
