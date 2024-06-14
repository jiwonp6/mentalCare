package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.CommentDTO;
import com.busanit.mentalCare.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/comments")
@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;

    // 수정 기능
    @PutMapping("/{comment_id}")
    ResponseEntity<CommentDTO> updateComment(@PathVariable Long comment_id, @RequestBody CommentDTO updatedComment) {
        CommentDTO comment = commentService.updateComment(comment_id, updatedComment);
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


    @DeleteMapping("/{comment_id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long comment_id) {
        if(!commentService.deleteComment(comment_id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

}
