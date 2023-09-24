package com.hgyr.blogpd.service;

import com.hgyr.blogpd.model.Board;
import com.hgyr.blogpd.model.Reply;
import com.hgyr.blogpd.repository.BoardRepository;
import com.hgyr.blogpd.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    @Autowired // DI
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void 글쓰기(Board board, String uid, String nickname) { // title, content
        board.setCount(0); // 조회수
        board.setUid(uid);
        board.setNickname(nickname);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> 글목록(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board 글상세보기(Long id) {
        return boardRepository.findById(id)
            .orElseThrow(()->{
                return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
            });
    }

    @Transactional
    public void 글삭제하기(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("글 찾기 실패 : 해당 글이 존재하지 않습니다.");
        });

        boardRepository.delete(board);
    }

    @Transactional
    public void 글수정하기(Long id, Board requestBoard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                });
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
    }

    @Transactional
    public void 댓글쓰기(String nickname, Long boardId, Reply requestReply) {

        Board board = boardRepository.findById(boardId)
                .orElseThrow(()->{
                    return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 id를 찾을 수 없습니다.");
                }); // 영속화 완료

        requestReply.setNickname(nickname);
        requestReply.setBoard(board);

        replyRepository.save(requestReply);
    }

    @Transactional
    public void 댓글삭제(Long replyId) {
        replyRepository.deleteById(replyId);
    }
}