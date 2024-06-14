package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 커스텀 메서드 - 글쓴이를 통해 게시글 찾기
    @Query("SELECT a FROM Board a WHERE a.user_nickname = :user_nickname")
    List<Board> findByUser(String user_nickname);

    // 커스텀 메서드 - 게시글 내용에 포함된 글자에서 게시글 찾기
    @Query("SELECT a FROM Board a WHERE a.board_content LIKE %:board_content%")
    List<Board> findByBoardContaining(String board_content);

    // 커스텀 메서드 - 게시글 제목에 포함된 글자에서 게시글 찾기
    @Query("SELECT a FROM Board a WHERE a.board_title LIKE %:board_title%")
    List<Board> findByTitleContaining(String board_title);

    // 커스텀 메서드 - 공감 삽입
    @Query("UPDATE a SET board.board_likeCount + 1 a WHERE a.board_id = :board_id")
    List<Board> addCountJPQL(String content_id);

    // 커스텀 메서드 - 공감 취소
    @Query("UPDATE a SET board.board_likeCount - 1 a WHERE a.board_id = :board_id")
    List<Board> subCountJPQL(String content_id);


}
