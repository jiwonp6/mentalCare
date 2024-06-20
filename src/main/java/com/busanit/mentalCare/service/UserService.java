package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.UserDto;
import com.busanit.mentalCare.model.User;
import com.busanit.mentalCare.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;



    // Entity -> DTO로 변환하여 전달
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(User::toDto).toList();
    }

    // 조회 (일부 기사)
    public UserDto getUserById(Long user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        return user.toDto();
    }

    // 게시글 생성
    @Transactional
    public UserDto createUser(@RequestBody UserDto dto) {
        User entity = dto.toEntity();
        System.out.println(entity);
        User saved = userRepository.save(entity);
        return saved.toDto();
    }

    // 업데이트
    @Transactional
    public UserDto updateUser(Long user_id, User updateUser) {
        User user = userRepository.findById(user_id).orElse(null);

        if(user != null) {
            if(updateUser.getUserId() != null) {
                user.setUserId(updateUser.getUserId());
            }
            if(updateUser.getUserPw() != null) {
                user.setUserPw(updateUser.getUserPw());
            }

            if(updateUser.getUserNickname() != null) {
                user.setUserNickname(updateUser.getUserNickname());
            }
            // 댓글의 게시글까지 변경하고 싶은 경우(로직 추가)
            User saved = userRepository.save(user);
            return saved.toDto();
        } else {
            return null;
        }
    }

    // 삭제
    @Transactional
    public Boolean deleteUser(Long user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        if(user != null) {
            userRepository.delete(user);
            return true;
        } else {
            return false;
        }
    }
}
