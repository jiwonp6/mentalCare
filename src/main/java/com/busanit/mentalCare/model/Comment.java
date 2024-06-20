package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    //@ManyToOne
    // @JoinColumn(name = "user_id")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "comment_content")
    private String commentContent;

    @CreatedDate
    @Column(name = "comment_time")
    private LocalDateTime commentTime;

    // 다 대 1 관계
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

//    // 대댓글에 사용할 삭제 유무 확인 (true = 삭제)
//    @ColumnDefault("FALSE")
//    @Column(nullable = false)
//    private Boolean isDeleted;
//
//    // comment에 parent 추가해야 할 상황일 듯...?
//    // 부모 댓글 -> null일 경우 최상위 댓글
//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "parent_id")
//    private Comment parentComment;

//    // 자식 댓글 -> 대댓글
//    @OneToMany(mappedBy = "parent", orphanRemoval = true)
//    private List<Comment> childrenComment = new ArrayList<>();

    // 엔티티 -> DTO 변환 메서드 (user에 대해서 선생님께 여쭤보기)
    public CommentDTO toDTO() {
        // 댓글에 게시글 ID가 없는 경우
        Long boardId = 0L;
        if(board != null) {
            boardId = board.getBoardId();
        }

        return new CommentDTO(commentId, commentContent, commentTime, user.getUserNickname(), boardId);

    }

    // DTO -> 엔티티로 변환
//    public static Comment createComment(CommentDTO dto) {
//        Comment comment = new Comment();
//
//        comment.setComment_id(dto.getComment_id());
//        comment.setUser_id(dto.getUser_id());
//
//        Board board = new Board();
//
//        return comment;
//    }



}
