package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.CommentDTO;
import com.busanit.mentalCare.model.Board;
import com.busanit.mentalCare.model.Comment;
import com.busanit.mentalCare.model.User;
import com.busanit.mentalCare.repository.BoardRepository;
import com.busanit.mentalCare.repository.CommentRepository;
import com.busanit.mentalCare.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;



    // 엔티티 -> DTO로 변환하여 전달
    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(Comment::toDTO).toList();
    }

//    public CommentDTO getCommentById(Long comment_id) {
//        Comment comment = commentRepository.findById(comment_id).orElse(null);
//        return comment.toDTO();
//    }

    @Transactional
    public CommentDTO createComment(CommentDTO dto) {
        Board board = boardRepository.findById(dto.getBoardId()).orElse(null);
        User user = userRepository.findByUserNickname(dto.getUserNickname());
        if(board == null) {
            throw new RuntimeException("존재하지 않은 게시판");
        }

        Comment comment = dto.toEntity(board, user);
        Comment saved = commentRepository.save(comment);
        return saved.toDTO();
    }

    @Transactional
    public CommentDTO updateComment(Long comment_id, CommentDTO updateComment) {
        Comment comment = commentRepository.findById(comment_id).orElse(null);

        if(comment != null) {
            if(updateComment.getCommentContent() != null) {
                comment.setCommentContent(updateComment.getCommentContent());
            }

            Comment saved = commentRepository.save(comment);
            return saved.toDTO();
        } else {
            return null;
        }
    }

    @Transactional
    public Boolean deleteComment(Long comment_id) {
        Comment comment = commentRepository.findById(comment_id).orElse(null);
        if(comment != null) {
            commentRepository.delete(comment);
            return true;
        } else {
            return false;
        }
    }





}