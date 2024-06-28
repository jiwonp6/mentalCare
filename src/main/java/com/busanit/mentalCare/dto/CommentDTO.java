package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.Board;
import com.busanit.mentalCare.model.ChildrenComment;
import com.busanit.mentalCare.model.Comment;
import com.busanit.mentalCare.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public  class CommentDTO {
    private Long commentId;
    private String commentContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy/MM/dd HH:mm")
    private LocalDateTime commentTime;
    private String userNickname;
    private Long boardId;
    private List<ChildrenCommentDTO> childrenComments;




    public Comment toEntity(Board board, User user) {
        Comment comment = Comment.builder()
                .commentId(commentId)
                .commentContent(commentContent)
                .commentTime(commentTime)
                .user(user)
                .board(board)
                .build();

        if(childrenComments != null) {
            List<ChildrenComment> childrenCommentList = childrenComments.stream().map(childrenCommentDTO ->
                    childrenCommentDTO.toEntity(comment, user)).toList();
            comment.setChildrenComments(childrenCommentList);
        }
        return comment;
    }

}

