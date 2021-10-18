package com.spring.mvc.reply.api;

import com.spring.mvc.reply.domain.Reply;
import com.spring.mvc.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController//비동기로 하는 것
@RequestMapping("/api/v1/reply")
@Log4j2
@RequiredArgsConstructor
@CrossOrigin//서버 투 서버에서 사용할 수 있게
public class ReplyApiController {

    private final ReplyService replyService;

    //댓글 목록 조회 요청처리
    @GetMapping("/{boardNo}")              //경로에서 변수 빼오기
    public ResponseEntity<List<Reply>> list(@PathVariable int boardNo) {//비동기로 하는 것ResponseEntity...
        log.info("/api/v1/reply/" + boardNo + "GET!");
        List<Reply> replyList = replyService.getList(boardNo);

        return new ResponseEntity<>(replyList, OK);
    }

    //댓글 등록 처리 요청
    //@RequestBody: 클라이언트에서 전달한 json데이터를 자바객체로 변환해줌
    @PostMapping("")//비워두면 맨위에 쓴거로 들어가나봄....
    public ResponseEntity<String> create(@RequestBody Reply reply) {
        log.info("/api/v1/reply POST! -" + reply);
        return replyService.register(reply)
                ? new ResponseEntity<>("insertSuccess", OK)
                : new ResponseEntity<>("insertFail", INTERNAL_SERVER_ERROR);
        //삼항연산자로.... 오.....
        //post요청은 무조건 form data가 필요한데.. 클라이언트가 form이 없을 수 있으니까 아이폰이라던가...
        //공통적으로 json을 넘겨주면 해석해서 넘겨주는...., json은 스트링..

    }

    //댓글 수정 요청처리
    @PutMapping("/{rno}")//주소에 있는걸 -------------->        매개변수로 받아서
    public ResponseEntity<String> modify(@PathVariable("rno") int replyNo,@RequestBody Reply reply) {
        reply.setReplyNo(replyNo);
        log.info("/api/v1/reply/" + replyNo + "PUT -" + reply);
        return replyService.modify(reply)//여기에 넣어줌 연결연결.....
                ? new ResponseEntity<>("modSuccess", OK)
                : new ResponseEntity<>("modFail", INTERNAL_SERVER_ERROR);
    }


    //댓글 삭제 요청 처리
    @DeleteMapping("/{rno}")
    public ResponseEntity<String> remove(@PathVariable("rno") int replyNo) {
        log.info("/api/v1/reply/" + replyNo +"DELETE");

        return replyService.remove(replyNo)
                ? new ResponseEntity<>("delSuccess", OK)
                : new ResponseEntity<>("delFail", INTERNAL_SERVER_ERROR);
    }

}
