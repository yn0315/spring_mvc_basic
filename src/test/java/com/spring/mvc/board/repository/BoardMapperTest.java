package com.spring.mvc.board.repository;

import com.spring.mvc.board.domain.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //테스트시에 스프링 컨테이너를 사용한다.
class BoardMapperTest {

    @Autowired
    BoardMapper boardMapper;

    @Test
    @DisplayName("300개의 게시물을 등록해야 한다.")
    void bulkInsert(){
        for (int i = 1; i <= 300 ; i++) {
            Board board = new Board();
            board.setTitle("테스트제목" + i);
            board.setContent("테스트내용이다" + i);
            board.setWriter("USER" + i);

            boardMapper.insertArticle(board);

        }
        System.out.println("게시물 등록 성공");
    }

    @Test
    @DisplayName("전체 게시물을 글번호 내림차순으로 조회해야 한다.")
    void selectAll() {
        List<Board> articles = boardMapper.getArticles();

        System.out.println("=================================================");
        for (Board article : articles) {
            System.out.println(article);
        }

        assertTrue(articles.size() == 300);//나는 아티클의 사이즈가 300개일거라고 단언한다.
    }

    @Test
    @DisplayName("글번호를 통해 1개의 게시물을 상세 조회해야 한다.")
    void selectOne() {
        Board content = boardMapper.getContent(30);
        assertEquals("USER30", content.getWriter());//같을 것이다라고 단언한다.(기대값, 실제값)
    }

    @Test
    @DisplayName("글번호를 통해 게시물을 1개 삭제해야 한다.")
    @Transactional
    @Rollback
    void delete() {
        boolean result = boardMapper.deleteArticle(300);
        Board content = boardMapper.getContent(300);

        assertTrue(result);//삭제성공하면 트루가 나올 것이다.
        assertNull(content);
    }

    @Test
    @DisplayName("글번호를 통해 게시물의 제목과 내용을 수정해야 한다.")
    void modify() {
        Board board = new Board();
        board.setBoardNo(50);
        board.setTitle("수정수정");
        board.setContent("메롱메롱메롱");

        boolean result = boardMapper.modifyArticle(board);
        Board content = boardMapper.getContent(50);

        assertTrue(result);
        assertEquals("수정수정", content.getTitle());
    }


}