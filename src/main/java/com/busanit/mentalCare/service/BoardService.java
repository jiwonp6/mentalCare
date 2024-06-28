package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.BoardDTO;
import com.busanit.mentalCare.model.*;
import com.busanit.mentalCare.repository.BoardRepository;
import com.busanit.mentalCare.repository.CommentRepository;
import com.busanit.mentalCare.repository.McUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private McUserRepository userRepository;


    // 모든 게시글 조회
    public List<BoardDTO> getAllBoards() {
        List<Board> boards = boardRepository.findAll();

        return boards.stream().map(Board::toDTO).toList();
    }

    public List<BoardDTO> getBoardByTagType(TagType tag) {
        List<Board> board = boardRepository.findByBoardTag(tag);
        return board.stream().map(Board::toDTO).toList();
    }

    public BoardDTO getBoardById(Long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        return board.toDTO();
    }




    // 게시글 생성
    @Transactional
    public BoardDTO createBoard(@RequestBody BoardDTO dto) {
        McUser user = userRepository.findByUserNickname(dto.getUserNickname());
        System.out.println(user);
        System.out.println("board entity:"+ dto.toEntity(user));

        Board saved = boardRepository.save(dto.toEntity(user));
        saved.setCalculateTime(Time.getTimeDifference(saved.getBoardTime(), LocalDateTime.now()));
        return saved.toDTO();
    }

    // 게시글 수정 (제목과 게시글 내용만 변경 가능)
    @Transactional
    public BoardDTO updateBoard(Long board_id, BoardDTO updateBoard) {
        Board board = boardRepository.findById(board_id).orElse(null);
        if (board != null) {
            // 게시글 제목 변경
            if (board.getBoardTitle() != null) {
                board.setBoardTitle(updateBoard.getBoardTitle());
            }
            // 게시글 내용 변경
            if (board.getBoardContent() != null) {
                board.setBoardContent(updateBoard.getBoardContent());
            }
            // 게시글 테그 변경
            if(board.getBoardTag() != null) {
                board.setBoardTag(updateBoard.getBoardTag());
            }
            // 글 작성자는 바꿀 수 없도록 함
            return boardRepository.save(board).toDTO();
        } else {
            return null;
        }
    }

    @Transactional
    public BoardDTO DeleteBoard(Long board_id) {
        Board board = boardRepository.findById(board_id).orElse(null);
        if(board != null) {
            boardRepository.delete(board);
            return board.toDTO();
        } else {
            return null;
        }
    }

    // 작성자를 통해 게시글 찾기
    public List<BoardDTO> getBoardByUserNickName(String userNickname) {
        List<Board> boardList = boardRepository.findByUserUserNickname(userNickname);
        return boardList.stream().map(Board::toDTO).toList();
    }

    // 포함된 글자를 통해 게시글 찾기
    public List<BoardDTO> getBoardByContentContaining(String boardContent) {
        List<Board> boardList = boardRepository.findByBoardContentContaining(boardContent);
        return boardList.stream().map(Board::toDTO).toList();
    }

    // 제목에 포함된 글자를 통해 게시글 찾기
    public List<BoardDTO> getBoardByTitleContaining(String boardTitle) {
        List<Board> boardList = boardRepository.findByBoardTitleContaining(boardTitle);
        return boardList.stream().map(Board::toDTO).toList();
    }

    public Board findBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId).orElse(null);
        return board;
    }

}
