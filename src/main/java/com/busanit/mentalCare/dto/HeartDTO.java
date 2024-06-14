package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.Board;
import com.busanit.mentalCare.model.Mc_user;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HeartDTO {
    private String user_id;
    private Long board_id;

    // 공감을 누른 user_id와 content_id 전달
    public HeartDTO(String user_id, Long board_id) {
        this.user_id = user_id;
        this.board_id = board_id;
    }
}
