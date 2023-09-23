package com.hgyr.blogpd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String content;
    
    @ManyToOne // 여러 개의 답변이 하나의 게시글에 존재
    @JoinColumn(name = "boardId")
    private Board board;

    private String nickname;

    @CreationTimestamp
    private Timestamp createDate;
}
