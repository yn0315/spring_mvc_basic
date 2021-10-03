package com.spring.mvc.score.repository;

import com.spring.mvc.score.domain.Score;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

//메모리에 성적정보들을 모아서 저장해야 한다.
@Repository//@Component랑 똑같은데 저장소의 의미가 더 명확함//스프링에 저장소 빈(요리재료)으로 등록
@Log4j2
public class MemoryScoreRepository implements  ScoreRepository{

    private static Map<Integer, Score> scoreMap = new HashMap<>();//구현...//분산되지 않게 static 붙여줌

    static  {
        scoreMap.put(1, new Score("김철수", 99, 78,67));
        scoreMap.put(2, new Score("박영희", 85, 89,100));
        scoreMap.put(3, new Score("고길동", 72, 86,42));
    }


    @Override
    public void save(Score score) {
        score.calcTotal();
        scoreMap.put(score.getStuNum(), score);
        log.info(scoreMap);
    }

    @Override
    public List<Score> findAll() {
        //Map에서 Score객체를 전부 뽑아낸 후 리스트에 담아 리턴
        List<Score>scores = new ArrayList<>();
        for ( Integer stuNum :scoreMap.keySet()) {
            Score score = scoreMap.get(stuNum);//맵은 반복문 못돌리니까 keyset으로 뽑아서 꺼냄
            scores.add(score);//리스트에 담아주는 거

        }
        return scores;
    }

    //점수 꺼내서 보여주는 처리
    @Override
    public Score findOne(int stuNum) {
           Score score = scoreMap.get(stuNum);

        return score;


    }

    @Override
    public void remove(int stuNum) {
        scoreMap.remove(stuNum);
    }


}
