package com.hgyr.blogpd.controller.api;

import com.hgyr.blogpd.dto.ResponseDto;
import com.hgyr.blogpd.model.Board;
import com.hgyr.blogpd.model.Reply;
import com.hgyr.blogpd.model.User;
import com.hgyr.blogpd.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService; // User 정보를 넘겨주기 위해 작성

    /* 글쓰기 */
    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @RequestBody User user) {
        user.setUid("hehe");
        boardService.글쓰기(board, user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    /*public ResponseDto<Integer> save(
            @RequestBody Board board,
            @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }*/

    /* 글 삭제 */
    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable Long id) {
        boardService.글삭제하기(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    /*public ResponseDto<Integer> deleteById(
            @PathVariable Long id,
            @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글삭제하기(id, principal);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }*/

    /* 글 수정 */
    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> update(@PathVariable Long id,@RequestBody Board board) {
        boardService.글수정하기(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    /* 댓글쓰기 */
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(
            @RequestBody User user,
            @PathVariable Long boardId,
            @RequestBody Reply reply) {

        boardService.댓글쓰기(user, boardId, reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    /*public ResponseDto<Integer> replySave(
            @PathVariable Long boardId,
            @RequestBody Reply reply,
            @AuthenticationPrincipal PrincipalDetail principal) {

        boardService.댓글쓰기(principal.getUser(), boardId, reply);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }*/

    /* 댓글 삭제 */
    // boardId 는 주소를 만들기 위해 파라미터로 받음 (replyId만 있으면 삭제 가능)
    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable Long replyId) {
        boardService.댓글삭제(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
