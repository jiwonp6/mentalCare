package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.BoardDTO;
import com.busanit.mentalCare.model.Board;
import com.busanit.mentalCare.model.User;
import com.busanit.mentalCare.repository.BoardRepository;
import com.busanit.mentalCare.repository.CommentRepository;
import com.busanit.mentalCare.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    // 모든 게시글 조회
    public List<BoardDTO> getAllBoards() {
        List<Board> boards = boardRepository.findAll();

        return boards.stream().map(Board::toDTO).toList();
    }


    // 게시글 생성
    @Transactional
    public BoardDTO createBoard(@RequestBody BoardDTO dto) {
        User user = userRepository.findByUserNickname(dto.getUserNickname());
        System.out.println(user);
        System.out.println("board entity:"+ dto.toEntity(user));

        Board saved = boardRepository.save(dto.toEntity(user));
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
            // 글 작성자는 바꿀 수 없도록 함
            return boardRepository.save(board).toDTO();
        } else {
            return null;
        }
    }

    // 게시글 삭제
    @Transactional
    public Boolean DeleteBoard(Long board_id) {
        Board board = boardRepository.findById(board_id).orElse(null);
        if(board != null) {
            boardRepository.delete(board);
            return true;
        } else {
            return false;
        }
    }

    // 저자를 통해 게시글 찾기
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



//    public List<BoardDTO> updateCountJPQL(String board_id, boolean b) {
//        List<Board> boardList;
//        if (b) {
//            boardList = boardRepository.addCountJPQL(board_id);
//        } else {
//            boardList = boardRepository.subCountJPQL(board_id);
//        }
//        return boardList.stream().map(Board::toDTO).toList();
//    }



}
