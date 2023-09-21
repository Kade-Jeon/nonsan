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
    private String uid;

    @NonNull // point 필드를 필수 필드로 지정
    // 추후 유효성 검사로 코드 고급화
    /* @PositiveOrZero(message = "Point must be a non-negative value")*/
    @Column
    private Long point;

    @NonNull
    @Column
    @NotBlank
    private  String nickName;

    // count 최소값 0,최대값 3으로 설정
    @Min(value = 0, message = "Count 는 0이하 불가")
    @Max(value = 3, message = "Count 는 3초과 불가")
    @Column
    private Integer count;

    @CreationTimestamp
    private LocalTime insertDateTime;

    @OneToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    private MyPoint myPoint;

}
