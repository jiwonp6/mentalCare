package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.Board;
import com.busanit.mentalCare.model.Comment;
import com.busanit.mentalCare.model.Mc_user;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long comment_id;
    private String comment_content;
    private LocalDateTime comment_date;
    private String user_id;
    private Long board_id;
    //private Comment parentComment;
    //private List<Comment> childrenComment;


    public Comment toEntity(Mc_user user, Board board) {

        return Comment.builder()
                .comment_id(comment_id)
                .comment_content(comment_content)
                .comment_time(comment_date)
                .user(user)
                .board(board)
                .build();

    }


    public static CommentDTO toDto(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setComment_id(comment.getComment_id());
        commentDTO.setComment_content(comment.getComment_content());
        commentDTO.setComment_date(comment.getComment_time());
        commentDTO.setUser_id(comment.getUser().getUser_id());
        commentDTO.setBoard_id(comment.getBoard().getBoard_id());
        return commentDTO;
    }
}
