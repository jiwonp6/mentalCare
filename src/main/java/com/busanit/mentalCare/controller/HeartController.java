package com.busanit.mentalCare.controller;

import com.busanit.mentalCare.model.McUser;
import com.busanit.mentalCare.model.User;
import com.busanit.mentalCare.service.HeartService;
import com.busanit.mentalCare.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boardHeart")
public class HeartController {

    private final HeartService heartService;
    private final UserService userService;

    @PostMapping("/up/{boardId}")
    public ResponseEntity<Map<String, Integer>> addHeart(@PathVariable("boardId") Long boardId, @RequestBody McUser user) {
        Integer count = heartService.addHeart(boardId, user);
        Map<String, Integer> map = new HashMap<>();
        map.put("count", count);
        System.out.println(count);
        System.out.println(map);
        return ResponseEntity.ok(map);
    }
}