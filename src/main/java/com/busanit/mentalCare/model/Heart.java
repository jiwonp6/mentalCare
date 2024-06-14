package com.busanit.mentalCare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
// 공감에 대한 정보를 저장하는 엔티티
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "heart_id")
    private Long heart_id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private Mc_user user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "content_id")
    private Board board;

    @Builder
    public Heart(Mc_user user, Board board) {
        this.user = user;
        this.board = board;
    }
}
