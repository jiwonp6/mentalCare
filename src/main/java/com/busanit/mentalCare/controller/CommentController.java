package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.CommentDTO;
import com.busanit.mentalCare.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/comment")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 수정 기능
    @PutMapping("/{commentId}")
    ResponseEntity<CommentDTO> updateComment(@PathVariable Long commentId,  @RequestBody CommentDTO updatedComment) {
        CommentDTO comment = commentService.updateComment(commentId, updatedComment);
        if(comment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comment);
    }

    @PostMapping
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO comment) {
        CommentDTO createdComment = commentService.createComment(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
    }


    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommentDTO> deleteComment(@PathVariable Long commentId) {
        CommentDTO commentDTO = commentService.deleteComment(commentId);
        if(commentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(commentDTO);
    }

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getAllComments() {
        List<CommentDTO> allComments = commentService.getAllComments();
        return ResponseEntity.ok(allComments);
    }

    @GetMapping("boardId/{boardId}")
    public List<CommentDTO> getCommentByBoardId(@PathVariable("boardId") Long boardId) {
        return commentService.getCommentByBoardId(boardId);
    }

}
