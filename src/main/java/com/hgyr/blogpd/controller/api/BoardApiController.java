package com.hgyr.blogpd.controller.api;

import com.hgyr.blogpd.dto.ResponseDto;
import com.hgyr.blogpd.dto.UserDto;
import com.hgyr.blogpd.model.Board;
import com.hgyr.blogpd.model.Reply;
import com.hgyr.blogpd.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.net.URI;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService; // User 정보를 넘겨주기 위해 작성

    /* 글쓰기 */
    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, HttpSession session) {
        String sid = session.getId();
        String uid = (String) session.getAttribute("user");
        System.out.println(sid + "::::::::" + uid);

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:1777")
                .path("/valid/user/"+uid)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto> responseEntity = restTemplate.postForEntity(uri, uid, UserDto.class);
        String nickName = responseEntity.getBody().getNickName();

        boardService.글쓰기(board, uid, nickName);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    /* 글 삭제 */
    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable Long id) {
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    /* 글 수정 */
    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable Long id, @RequestBody Board board) {
        boardService.글수정하기(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    /* 댓글쓰기 */
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(
            HttpSession session,
            @PathVariable Long boardId,
            @RequestBody Reply reply) {

        String sid = session.getId();
        String uid = (String) session.getAttribute("user");
        System.out.println(sid + "::::::::" + uid);

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:1777")
                .path("/valid/user/"+uid)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto> responseEntity = restTemplate.postForEntity(uri, uid, UserDto.class);
        String nickName = responseEntity.getBody().getNickName();

        boardService.댓글쓰기(nickName, boardId, reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    /* 댓글 삭제 */
    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable Long replyId) {
        boardService.댓글삭제(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
