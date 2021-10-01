package com.spring.mvc.score.service;

import com.spring.mvc.score.domain.Score;
import com.spring.mvc.score.repository.MemoryScoreRepository;
import com.spring.mvc.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//컨트롤러와 저장소 중간에서 데이터 중간처리를 담당하는 클래스
@Log4j2
@Service//컴포넌트와 동일하지만 따로 만들어져 있음..
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;//컨트롤러와 저장소 중간에서 저장소에 보내는 역할이기 때문에..........

    //점수정보 저장 전에 해야할 처리
    public void register(Score score) {
        score.calcTotal();
        scoreRepository.save(score);

    }




}
