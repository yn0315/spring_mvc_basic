package com.spring.mvc.board.controller;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    //게시물 목록 요청
    @GetMapping("/list")
    public String list(Model model) {
        log.info("/board/list GET 요청 발생!");
        List<Board> articles = boardService.getArticles();
        model.addAttribute("articles", articles);
        return "board/list";
    }

    //게시물 상세조회요청
    @GetMapping("/content")
    public String content(int boardNo, Model model) {
        log.info("/board/content GET요청발생- 글번호:" + boardNo);
        Board article = boardService.getContent(boardNo);
        model.addAttribute("article", article);
        return "board/content";
    }

    //게시물작성
    @GetMapping("/write")
    public String write(Model model) {
        log.info("board/write GET 요청발생");
        return "board/write";
    }

    @PostMapping("/write")
    public String write(Board board, RedirectAttributes ra) {//리다이렉트할 때는 모델 대신 리다이렉트어트리~
        log.info("/board/write POST 요청" + board);

        if(boardService.insert(board)) {
            ra.addFlashAttribute("msg", "success");

        }else {
            ra.addFlashAttribute("msg", "fail");
        }

        return "redirect:/board/list";
    }


    //게시물삭제
    @GetMapping("/delete")
    public String delete(int boardNo) {
        log.info("/board/delete GET-"+ boardNo);
        boardService.remove(boardNo);

        return "redirect:/board/list";
    }

}
