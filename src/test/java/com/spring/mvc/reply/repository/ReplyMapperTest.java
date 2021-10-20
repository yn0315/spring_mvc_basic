package com.spring.mvc.reply.repository;


import com.spring.mvc.common.paging.Page;
import com.spring.mvc.reply.domain.Reply;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest//new생성 안하고 의존성 주입받으려면 이거 써줘야함
class ReplyMapperTest {

    @Autowired
    ReplyMapper replyMapper;

    @Test
    @DisplayName("특정 글번호 게시글에 댓글 20개를 삽입해야 한다.")
    void insertTest() {
        for (int i = 1; i <=200; i++) {
            Reply reply = new Reply();
            reply.setBoardNo(309);
            reply.setReplyText("테스트댓글" + i);
            reply.setReplyWriter("짹짹이" + i);

            replyMapper.save(reply);
        }


    }
    @Test
    @DisplayName("특정 게시물의 댓글 목록을 조회할 수 있어야 한다.")
    void getListTest() {
        List<Reply> list = replyMapper.getList(309, new Page(1,10));
        System.out.println("=============================================================");
        for (Reply reply : list) {
            System.out.println(reply);
        }
        assertTrue(list.size() == 10);
    }

    @Test
    @DisplayName("특정 게시물을 수정할 수 있어야 한다.")
    void updateTest() {
        Reply reply = replyMapper.read(15);
        reply.setReplyText("수정된 댓글");

        replyMapper.update(reply);

        assertEquals(reply.getReplyText(), replyMapper.read(15).getReplyText());
    }

    @Test
    @DisplayName("특정 게시물을 삭제할 수 있어야 한다.")
    @Transactional
    @Rollback//테스트시에만 삭제되는 거라 실제 db에서는 안 날아감
    void deleteTest() {
        replyMapper.delete(11);
        replyMapper.delete(12);

        assertTrue(replyMapper.getList(309, new Page(1,10)).size() == 18);
    }


}