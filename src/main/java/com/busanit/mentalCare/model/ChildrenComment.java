package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.ChildrenCommentDTO;
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
@Table(name = "children_comment")
@EntityListeners(AuditingEntityListener.class)
public class ChildrenComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "children_id", nullable = false)
    private Long childrenId;

    //@ManyToOne
    // @JoinColumn(name = "user_id")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "children_content")
    private String childrenContent;

    @CreatedDate
    @Column(name = "children_time")
    private LocalDateTime childrenTime;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;


    // 엔티티 -> DTO 변환 메서드
    public ChildrenCommentDTO toDTO() {
        Long commentId = 0L;
        if(comment != null) {
            commentId = comment.getCommentId();
        }
        return new ChildrenCommentDTO(childrenId, childrenContent, childrenTime, user.getUserNickname(), commentId);

    }
}
