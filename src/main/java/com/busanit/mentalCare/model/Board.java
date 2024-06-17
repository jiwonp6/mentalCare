package com.busanit.mentalCare.model;

import com.busanit.mentalCare.dto.BoardDTO;
import com.busanit.mentalCare.dto.CommentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "content")
public class Board {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // 자동 생성
    private Long board_id;

    @Column(name = "board_tag")
    private TagType board_tag;

    @Column(name = "board_title")
    private String board_title;

    @Column(name = "user_id")
    private Mc_user user;

    @Column(name = "board_date")
    private LocalDateTime board_date;

    @Column(name = "board_content")
    private String board_content;

    // 1 대 다 관계 (content - comment)
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    // 공감 갯수를 담을 필드
    @ColumnDefault("0")
    @Column(name = "likeCount", nullable = false)
    private Integer board_likeCount;

    // 엔티티 -> DTO
    public BoardDTO toDTO() {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        String userId = null;
        if(comments != null) {
            commentDTOList = comments.stream().map(Comment::toDTO).toList();
            userId = user.getUser_id();
        }
        return new BoardDTO(board_id, board_tag, board_title, userId, board_date, board_content, board_likeCount, commentDTOList);
    }

//    // 엔티티 -> DTO 변환 메서드
//    public static Board createContent(BoardDTO dto) {
//        Board board = new Board();
//        board.setBoard_id(dto.getBoard_id());
//        board.setBoard_title(dto.getBoard_title());
//        board.setBoard_content(dto.getBoard_content());
//        board.setBoard_tag(dto.getBoard_tag());
//        board.setUser_id(dto.getUser_id());
//        board.setBoard_date(dto.getBoard_date());
//        return board;
//    }
}
