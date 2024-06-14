package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.dto.HeartDTO;
import com.busanit.mentalCare.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("board/heart")
public class HeartController {
    private final HeartService heartService;

    // 공감을 추가하는 기능 (공감 버튼을 눌렀을 때)
    @PostMapping
    public ResponseEntity<HeartDTO> insert(@RequestBody HeartDTO heart) throws Exception {
        HeartDTO insertHeart = heartService.insert(heart);
        return ResponseEntity.status(HttpStatus.CREATED).body(insertHeart);
    }

    // 공감을 취소하는 기능 (공감 버튼을 눌렀을 때)
    @PostMapping
    public ResponseEntity<HeartDTO> delete(@RequestBody HeartDTO heart) throws Exception {
        HeartDTO deleteHeart = heartService.delete(heart);
        return ResponseEntity.status(HttpStatus.CREATED).body(deleteHeart);
    }


}
