package com.busanit.mentalCare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "heart_id")
    private Long heartId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId")
    private McUser user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Heart (McUser user, Board board) {
        this.user = user;
        this.board = board;
    }
}
