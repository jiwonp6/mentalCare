package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.ChildrenCommentDTO;
import com.busanit.mentalCare.service.ChildrenCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/children")
@RestController
public class ChildrenCommentController {

    @Autowired
    private ChildrenCommentService childrenService;

    // 수정 기능
    @PutMapping("/{childrenId}")
    ResponseEntity<ChildrenCommentDTO> updateChildren(@PathVariable Long childrenId, @RequestBody ChildrenCommentDTO updatedChildren) {
        ChildrenCommentDTO children = childrenService.updateChildren(childrenId, updatedChildren);
        if(children == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(children);
    }

    @PostMapping
    public ResponseEntity<ChildrenCommentDTO> createChildren(@RequestBody ChildrenCommentDTO children) {
        ChildrenCommentDTO createdChildren = childrenService.createChildren(children);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdChildren);
    }


    @DeleteMapping("/{childrenId}")
    public ResponseEntity<ChildrenCommentDTO> deleteChildren(@PathVariable Long childrenId) {
        ChildrenCommentDTO childrenCommentDTO = childrenService.deleteChildren(childrenId);
        if(childrenCommentDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(childrenCommentDTO);
    }

    @GetMapping
    public ResponseEntity<List<ChildrenCommentDTO>> getAllChildren() {
        List<ChildrenCommentDTO> allChildren = childrenService.getAllChildren();
        return ResponseEntity.ok(allChildren);
    }

    @GetMapping("commentId/{commentId}")
    public List<ChildrenCommentDTO> getChildrenByCommentId(@PathVariable("commentId") Long commentId) {
        return childrenService.getChildrenByCommentId(commentId);
    }

}
