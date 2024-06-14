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

    private BoardService boardService;

    // Insert (게시글 생성)
    @PostMapping
    public ResponseEntity<BoardDTO> createBoard (@RequestBody BoardDTO board) {
        BoardDTO createdBoard = boardService.createBoard(board);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
    }


    // update (게시글 수정)
    @PutMapping("/{id}")
    public ResponseEntity<BoardDTO> updateBoard(@PathVariable Long board_id, @RequestBody BoardDTO updateBoard) {
        BoardDTO board = boardService.updateBoard(board_id, updateBoard);

        if(board == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("user_id/{user_id}")
    public List<BoardDTO> getContentByUser(@PathVariable String user_id) {
        return boardService.getBoardByUser(user_id);
    }

    @GetMapping("board_content/{board_content}")
    public List<BoardDTO> getBoardByContentContaining(@PathVariable String board_content) {
        return boardService.getBoardByContentContaining(board_content);
    }

    @GetMapping("board_title/{board_title}")
    public List<BoardDTO> getBoardByTitleContaining(@PathVariable String board_title) {
        return boardService.getBoardByTitleContaining(board_title);
    }

    // 공감에 대한 기능 (공감 삽입/ 공감 취소)
    @GetMapping("/{board_heart}")
    public List<BoardDTO> updateHeartCount(@PathVariable String board_id, boolean b ) {
        return boardService.updateCountJPQL(board_id, b);
    }


}
