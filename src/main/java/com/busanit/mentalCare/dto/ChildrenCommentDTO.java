package com.busanit.mentalCare.dto;

import com.busanit.mentalCare.model.ChildrenComment;
import com.busanit.mentalCare.model.Comment;
import com.busanit.mentalCare.model.McUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChildrenCommentDTO {
    private Long childrenId;
    private String childrenContent;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yy/MM/dd HH:mm")
    private LocalDateTime childrenTime;
    private String userNickname;
    private Long commentId;



    public ChildrenComment toEntity(Comment comment, McUser user) {
        return ChildrenComment.builder()
                .childrenId(childrenId)
                .childrenContent(childrenContent)
                .childrenTime(childrenTime)
                .user(user)
                .comment(comment)
                .build();
    }
}
