package com.hgyl.project5.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyPoint {

    @Id
    @Size(min = 4, max = 50)
    @Column(name = "my_point_uid")
    private String uid;

    @NonNull // point 필드를 필수 필드로 지정
    // 추후 유효성 검사로 코드 고급화
   /* @PositiveOrZero(message = "Point must be a non-negative value")*/
    private Double point;

    @Builder.Default // addPoint 필드의 기본값을 0L로 설정
    private Double addPoint = Double.valueOf(0L);

    @Builder.Default // minusPoint 필드의 기본값을 0L로 설정
    private Double minusPoint = Double.valueOf(0L);

    @NonNull // nickName 필드를 필수 필드로 지정
    @Column
    @NotBlank
    private String nickName;
}
