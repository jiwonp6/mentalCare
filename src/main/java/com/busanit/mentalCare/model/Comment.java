package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.ChildrenCommentDTO;
import com.busanit.mentalCare.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener .class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    //@ManyToOne
    // @JoinColumn(name = "user_id")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private McUser user;

    @Column(name = "comment_content")
    private String commentContent;

    @CreatedDate
    @Column(name = "comment_time")
    private LocalDateTime commentTime;

    // 다 대 1 관계
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;


    @OneToMany(mappedBy = "comment", orphanRemoval = true)
    private List<ChildrenComment> childrenComments;



    // 엔티티 -> DTO 변환 메서드 (user에 대해서 선생님께 여쭤보기)
    public CommentDTO toDTO() {
        // 댓글에 게시글 ID가 없는 경우
        Long boardId = 0L;
        if(board != null) {
            boardId = board.getBoardId();
        }

        // 댓글에 대한 답글
        List<ChildrenCommentDTO> childrenDTOList = new ArrayList<>();
        if(childrenComments != null) {
            childrenDTOList = childrenComments.stream().map(ChildrenComment::toDTO).toList();
        }
        return new CommentDTO(commentId, commentContent, commentTime, user.getUserNickname(), boardId, childrenDTOList);

    }
}
