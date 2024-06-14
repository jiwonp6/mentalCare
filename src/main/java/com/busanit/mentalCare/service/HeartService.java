package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.HeartDTO;
import com.busanit.mentalCare.model.Board;
import com.busanit.mentalCare.model.Heart;
import com.busanit.mentalCare.model.Mc_user;
import com.busanit.mentalCare.repository.BoardRepository;
import com.busanit.mentalCare.repository.HeartRepository;
import com.busanit.mentalCare.repository.Mc_userRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartService {

    // 전달받은 데이터(content_id, user_id)를 통해 각각 user와 content를 조회하고 저장 혹은 삭제 진행
    private final HeartRepository heartRepository;
    private final Mc_userRepository userRepository;
    private final BoardRepository boardRepository;



    // 공감 삽입
    @Transactional
    public HeartDTO insert(HeartDTO heartDTO) throws Exception {
        Mc_user user = userRepository.findByUserId(heartDTO.getUser_id());
        Board board = boardRepository.findById(heartDTO.getBoard_id()).orElse(null);

        // 이미 공감 버튼을 눌렀으면 에러 반환
        if (heartRepository.findByUserAndContent(user, board).isPresent()) {
            throw new Exception();
        }

        Heart contentHeart = Heart.builder()
                .board(board)
                .user(user)
                .build();

        heartRepository.save(contentHeart);
        return heartDTO;
    }

    // 공감 삭제
    @Transactional
    public HeartDTO delete(HeartDTO heartDTO) {
        Mc_user user = userRepository.findByUserId(heartDTO.getUser_id());
        Board board = boardRepository.findById(heartDTO.getBoard_id()).orElse(null);
        Heart heart = heartRepository.findByUserAndContent(user, board).orElse(null);

        heartRepository.delete(heart);

        return heartDTO;
    }

}
