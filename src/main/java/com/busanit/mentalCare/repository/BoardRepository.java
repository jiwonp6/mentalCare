package com.busanit.mentalCare.repository;

import com.busanit.mentalCare.model.Board;
import com.busanit.mentalCare.model.TagType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    // 커스텀 메서드 - 글쓴이(닉네임)를 통해 게시글 찾기
    List<Board> findByUserUserNickname(String userNickname);

    // 커스텀 메서드 - 게시글 내용에 포함된 글자에서 게시글 찾기
    List<Board> findByBoardContentContaining(String board_content);

    // 커스텀 메서드 - 게시글 제목에 포함된 글자에서 게시글 찾기
    List<Board> findByBoardTitleContaining(String board_title);

    List<Board> findByBoardTag(TagType tag);


}

