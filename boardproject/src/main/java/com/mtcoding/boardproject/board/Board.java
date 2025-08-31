package com.mtcoding.boardproject.board;

import com.mtcoding.boardproject.user.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Entity
@Table(name = "board_tb")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가
    private Integer id;
    private String title;
    private String content;

    @CreationTimestamp  // 자동으로 현재 시간 저장
    private Timestamp createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder  // 객체 생성 용도
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
