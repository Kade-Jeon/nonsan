package com.hgyl.project5.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Roulette {

    @Id
    @Size(min = 4, max = 50)
    @Column(name = "roulette_uid")
    private String uid;

    // count 최소값 0,최대값 3으로 설정
    @Min(value = 0, message = "Count 는 0이하 불가")
    @Max(value = 10, message = "Count 는 10초과 불가")
    private Integer count;

    @OneToOne
    private MyPoint myPoint;
}
