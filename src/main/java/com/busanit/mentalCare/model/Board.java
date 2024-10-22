package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.BoardDTO;
import com.busanit.mentalCare.dto.CommentDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "board")
public class Board {

    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동 생성
    private Long boardId;

    @Enumerated(EnumType.STRING)
    @Column(name = "board_tag")
    private TagType boardTag;

    @Column(name = "board_title")
    private String boardTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private McUser user;

    @CreatedDate
    @Column(name = "board_time")
    private LocalDateTime boardTime;

    private String calculateTime;

    public void setCalculateTime(String calculateTime) {
        this.calculateTime = Time.getTimeDifference(boardTime, LocalDateTime.now());
    }

    @Column(name = "board_content")
    private String boardContent;


    // 1 대 다 관계 (content - comment) -> 양방향 관계가 아니면 굳이 필요 없음
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Comment> comments;

    // 공감 갯수를 담을 필드
    @ColumnDefault("0")
    @Column(name = "board_like_count", nullable = false)
    private Integer boardLikeCount;

    @ColumnDefault("0")
    @Column(name = "board_comment_count", nullable = false)
    private int boardCommentCount;

    // 엔티티 -> DTO
    public BoardDTO toDTO() {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        if(comments != null) {
            commentDTOList = comments.stream().map(Comment::toDTO).toList();
        }
        return new BoardDTO(boardId, boardTag, boardTitle, boardTime, calculateTime, boardContent,
                user.getUserNickname(), boardLikeCount, boardCommentCount, commentDTOList);
    }

}