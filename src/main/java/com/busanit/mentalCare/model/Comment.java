package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long comment_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Mc_user user;

    @Column(name = "comment_content")
    private String comment_content;

    @Column(name = "comment_time")
    private LocalDateTime comment_time;

    // 다 대 1 관계
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    // 대댓글에 사용할 삭제 유무 확인 (true = 삭제)
    @ColumnDefault("FALSE")
    @Column(nullable = false)
    private Boolean isDeleted;


    // 엔티티 -> DTO 변환 메서드 (user에 대해서 선생님께 여쭤보기)
    public CommentDTO toDTO() {
        // 댓글에 게시글 ID가 없는 경우
        Long boardId = 0L;
        String userId = null;
        if(board != null) {
            boardId = board.getBoard_id();
            userId = user.getUser_id();
        }

        return new CommentDTO(comment_id, comment_content, comment_time, userId, boardId);
    }





}
