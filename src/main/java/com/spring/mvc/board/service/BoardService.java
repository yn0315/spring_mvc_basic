package com.spring.mvc.board.service;

import com.spring.mvc.board.domain.Board;
import com.spring.mvc.board.repository.BoardMapper;
import com.spring.mvc.common.paging.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor//하나만 있을 때 파이널을 초기화해주는 생성자
public class BoardService {

    private final BoardMapper boardMapper;//의존관계 형성
    //알트엔터 생성자 매개변수추가

//    @Autowired
//    public BoardService(BoardMapper boardMapper) {
//        this.boardMapper = boardMapper;
//    }
//

    //게시물 목록 가져오기
    public List<Board> getArticles(Page page) {
        List<Board> articles = boardMapper.getArticles(page);

        //3분 이내 신규글 new마크 붙이기
        for (Board article : articles) {

            //각 게시물들의 등록시간을 읽어오기(밀리초 유닉스타임으로부터 몇 초 지났니..)
            long regTime = article.getRegDate().getTime();

            //현재시간 읽어오기 마이바티스는 로컬데이트 안됨
            // (밀리초)
            long now = System.currentTimeMillis();

            //ex) 초 * 분 * 시간 * 1000
            if(now- regTime < 60 * 3 * 1000) {
                article.setNewFlag(true);
            }

        }//end new mark

        //조회수가 10이 넘어가면 hit마크 붙이기
        for (Board article : articles) {
            if(article.getViewCnt() >= 10) {
                article.setHitFlag(true);
            }
        }//end hit mark

        return articles;

    }
    //총 게시물 수 조회
    public int getCount() {
        return boardMapper.getTotalCount();
    }

    //게시글 상세조회
    @Transactional//둘다 제대로 작동 안할시 자동으로 롤백해줌
    public Board getContent(int boardNo) {
        Board content = boardMapper.getContent(boardNo);
        boardMapper.upViewCount(boardNo);//조회수 올리기
        return content;
    }

    //게시물 수정
    public boolean modifyContent(Board board) {
        downViewCount(board.getBoardNo());
        return boardMapper.modifyArticle(board);
    }

    //게시글 등록
    public boolean insert(Board board){
        return boardMapper.insertArticle(board);//트루를 리턴하니까 이렇게....
    }

    //게시물 삭제
    public boolean remove(int boardNo){
        return boardMapper.deleteArticle(boardNo);
    }

    //조회수 고정
    public void downViewCount(int boardNo) {
       boardMapper.downViewCount(boardNo);
    }
}
