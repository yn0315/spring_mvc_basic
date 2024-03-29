package com.spring.mvc.score.controller;

import com.spring.mvc.score.domain.Score;
import com.spring.mvc.score.repository.ScoreMapper;
import com.spring.mvc.score.repository.ScoreRepository;
import com.spring.mvc.score.service.ScoreService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Log4j2 //로그출력을 도와주는 기능
//@RequiredArgsConstructor //final필드를 초기화하는 생성자 자동생성, 클래스가 하나만 존재할 때 사용!!
public class ScoreController {

    private final ScoreRepository scoreRepository; //의존관계 형성// 한번 결정되면 끝날 때까지 저장소가 바뀌지 않도록 final씀//모든 걸 서비스에 위임하면 삭제...
    private final ScoreService scoreService;

    private final ScoreMapper scoreMapper;

    @Autowired
    public ScoreController(@Qualifier("jr") ScoreRepository scoreRepository, ScoreService scoreService, ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
        this.scoreRepository = scoreRepository;
        this.scoreService = scoreService;
    }

    //    @Autowired//주입
//    public ScoreController(ScoreRepository scoreRepository) {
//        this.scoreRepository = scoreRepository;
//    }// @RequiredArgsConstructor 쓰면 자동으로 작성해줘서 안 써도 됨



    //점수프로그램 화면요청
    @GetMapping("/score/list")
    public String scoreList(Model model) {
//        List<Score> scores = scoreRepository.findAll();
        List<Score> scores = scoreMapper.findAll();
        model.addAttribute("scoreList", scores);
        return "score/score-list";
    }

    //점수정보 저장요청
    @PostMapping("/score/register")
    public String register(Score score) {//기본생성자 + setter씀
        log.info("점수 등록 요청! - " + score);//로깅...시간정보를 찍어줘야함...lombok에서 제공하는 @Log4j2 쓰고 이렇게 작성해야 함

        scoreService.register(score);
        //리다이렉트는 재요청기능입니다.
        return "redirect:/score/list";//리다이렉트에서는 jsp 가 아니라 요청url을 쓰는 것....

    }

    //점수정보 삭제요청처리
    @GetMapping("/score/delete")
    public String delete(int stuNum) {
        log.info("점수 삭제요청-");
//        scoreRepository.remove(stuNum);
        scoreMapper.remove(stuNum);
        return "redirect:/score/list";
    }

    //상세정보 페이지
    @GetMapping("/score/detail")//매개변수 sn으로 쓰고 싶으면 @RequestParam("stuNum") int sn,
    public String detail( int stuNum, Model model) {
//        Score score = scoreRepository.findOne(stuNum);
        Score score = scoreMapper.findOne(stuNum);
        //findOne호출해서 리턴받아 Score에 대입하고
        //그 score를 모델을 통해 보내면..???


        model.addAttribute("score", score);
        return "/score/detail";
    }




}
