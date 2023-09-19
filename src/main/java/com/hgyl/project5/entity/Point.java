package com.hgyl.project5.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Point {

    @Id
    @Column(length = 100)
    private String uid;

    @Column
    private Long point;

    private Long addPoint;

    private Long minusPoint;

    @Column(length = 300)
    private String nickName;

    @CreationTimestamp
    private LocalTime insertDateTime;
}
