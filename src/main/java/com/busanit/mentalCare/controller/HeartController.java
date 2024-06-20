package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.model.User;
import com.busanit.mentalCare.service.HeartService;
import com.busanit.mentalCare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boardHeart")
public class HeartController {

    private final HeartService heartService;
    private final UserService userService;

    @PostMapping("up/{boardId}")
    public ResponseEntity addHeart(@PathVariable("boardId") Long boardId, @RequestBody User user) {
        heartService.addHeart(boardId, user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }




}