package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.BoardDTO;
import com.busanit.mentalCare.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // Insert (게시글 생성)
    @PostMapping
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO board) {
        BoardDTO createdBoard = boardService.createBoard(board);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
    }


    // select (게시글 검색) - 커스텀 메서드
    @GetMapping("userNickname/{userNickname}")
    public List<BoardDTO> getBoardByUserNickName(@PathVariable("userNickname") String userNickName) {
        return boardService.getBoardByUserNickName(userNickName);
    }

    @GetMapping("content/{boardContent}")
    public List<BoardDTO> getBoardByBoardContaining(@PathVariable String boardContent) {
        return boardService.getBoardByContentContaining(boardContent);
    }

    @GetMapping("title/{boardTitle}")
    public List<BoardDTO> getBoardByTitleContaining(@PathVariable String boardTitle) {
        return boardService.getBoardByTitleContaining(boardTitle);
    }

    @GetMapping
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        List<BoardDTO> boards = boardService.getAllBoards();
        return ResponseEntity.ok(boards);
    }


    // update (게시글 수정)
    @PutMapping("update/{boardId}")
    public ResponseEntity<BoardDTO> updateBoard(@PathVariable Long boardId, @RequestBody BoardDTO updateBoard) {
        BoardDTO board = boardService.updateBoard(boardId, updateBoard);

        if (board == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    // DELETE (게시글 삭제)
    @DeleteMapping("delete/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId) {
        if (!boardService.DeleteBoard(boardId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
