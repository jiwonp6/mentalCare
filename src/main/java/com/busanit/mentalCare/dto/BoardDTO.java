package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private Long boardId;
    private TagType boardTag;
    private String boardTitle;
    private LocalDateTime boardTime;
    private String calculateTime;

    public void setCalculateTime(String calculateTime) {
        this.calculateTime = Time.getTimeDifference(boardTime, LocalDateTime.now());
    }

    public String getCalculateTime() {
        return Time.getTimeDifference(boardTime, LocalDateTime.now());
    }

    private String boardContent;
    private String userNickname;
    private int boardLikeCount;
    private int boardCommentCount;
    private List<CommentDTO> comments;


    // DTO -> Entity (엔티티에 @Builder 적용, 빌더 패턴 적용)
    public Board toEntity(McUser user) {
        // DTO -> 엔티티 필드로 매핑
        Board board = Board.builder()
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardTag(boardTag)
                .user(user)
                .boardTime(boardTime)
                .calculateTime(calculateTime)
                .boardLikeCount(boardLikeCount)
                .boardCommentCount(boardCommentCount)
                .build();

        // DTO (댓글 리스트) -> 엔티티 (댓글 리스트)
        if(comments != null) {
            List<Comment> commentList = comments.stream().map(commentDTO ->
                    commentDTO.toEntity(board, user)).toList();
            board.setComments(commentList);
        }
        return board;
    }
}
