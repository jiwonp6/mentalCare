package com.busanit.mentalCare.service;

import com.busanit.mentalCare.model.Board;
import com.busanit.mentalCare.model.Heart;
import com.busanit.mentalCare.model.McUser;
import com.busanit.mentalCare.repository.BoardRepository;
import com.busanit.mentalCare.repository.HeartRepository;
import com.busanit.mentalCare.repository.McUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class HeartService {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final HeartRepository heartRepository;
    private final McUserRepository userRepository;

    public Integer addHeart(Long boardId, McUser user) {

        Board board = boardService.findBoardId(boardId);
        McUser findUser = userRepository.findByUserId(user.getUserId());
        if(!heartRepository.existsByUserAndBoard(findUser, board)) {
            // 호출되면 board에 있는 count 증가
            board.setBoardLikeCount(board.getBoardLikeCount()+1);
            // heartRepository에 userId랑 boardId값 저장
            heartRepository.save(new Heart(user, board));
            return board.getBoardLikeCount();
        } else {
            board.setBoardLikeCount(board.getBoardLikeCount()-1);
            heartRepository.deleteByUserAndBoard(user, board);
            return board.getBoardLikeCount();
        }
    }


}
