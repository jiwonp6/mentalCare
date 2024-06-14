package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.Board;
import com.busanit.mentalCare.model.Comment;
import com.busanit.mentalCare.model.Mc_user;
import com.busanit.mentalCare.model.TagType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long board_id;
    private TagType board_tag;
    private String board_title;
    private String user_id;
    private Date board_date;
    private String board_content;
    private Integer board_likeCount;
    private List<CommentDTO> comments;


    // DTO -> Entity (엔티티에 @Builder 적용, 빌더 패턴 적용)
    public Board toEntity(Mc_user user) {
        // DTO -> 엔티티 필드로 매핑
        Board board = Board.builder()
                .board_title(board_title)
                .board_content(board_content)
                .board_tag(board_tag)
                .user(user)
                .board_date(board_date)
                .board_content(board_content)
                .board_likeCount(board_likeCount)
                .build();

        // DTO (댓글 리스트) -> 엔티티 (댓글 리스트)
        if(comments != null) {
            List<Comment> commentList = comments.stream().map(commentDTO ->
                    commentDTO.toEntity(user, board)).toList();
            board.setComments(commentList);
        }
        return board;
    }
}
