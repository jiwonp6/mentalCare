package com.busanit.mentalCare.service;

import com.busanit.mentalCare.dto.CommentDTO;
import com.busanit.mentalCare.model.Board;
import com.busanit.mentalCare.model.Comment;
import com.busanit.mentalCare.model.McUser;
import com.busanit.mentalCare.model.User;
import com.busanit.mentalCare.repository.BoardRepository;
import com.busanit.mentalCare.repository.CommentRepository;
import com.busanit.mentalCare.repository.McUserRepository;
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
    private McUserRepository userRepository;


    // 엔티티 -> DTO로 변환하여 전달
    @Transactional
    public List<CommentDTO> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream().map(Comment::toDTO).toList();
    }

    @Transactional
    public List<CommentDTO> getCommentByBoardId(Long boardId) {
        List<Comment> commentList = commentRepository.findByBoardBoardId(boardId);
        return commentList.stream().map(Comment::toDTO).toList();
    }

    @Transactional
    public CommentDTO createComment(CommentDTO dto) {
        Board board = boardRepository.findById(dto.getBoardId()).orElse(null);
        McUser user = userRepository.findByUserNickname(dto.getUserNickname());
        if(board == null) {
            throw new RuntimeException("존재하지 않은 게시판");
        }

        Comment comment = dto.toEntity(board, user);
        int boardCommentCount = comment.getBoard().getBoardCommentCount();
        comment.getBoard().setBoardCommentCount(boardCommentCount + 1);
        Comment saved = commentRepository.save(comment);
        return saved.toDTO();
    }

    @Transactional
    public CommentDTO updateComment(Long comment_id,  CommentDTO updateComment) {
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
    public CommentDTO deleteComment(Long comment_id) {
        Comment comment = commentRepository.findById(comment_id).orElse(null);
        if(comment != null) {
            int boardCommentCount = comment.getBoard().getBoardCommentCount();
            if(comment.getChildrenComments().isEmpty()) {
                commentRepository.delete(comment);
                comment.getBoard().setBoardCommentCount(boardCommentCount - 1);
            } else {
                comment.setCommentContent("삭제된 댓글입니다.");
                comment.getBoard().setBoardCommentCount(boardCommentCount - 1);
            }
            return comment.toDTO();
        } else {
            return null;
        }
    }

}