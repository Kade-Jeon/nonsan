package com.hgyl.project5.dto;

import com.hgyl.project5.entity.Point;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalTime;

@NoArgsConstructor
@Getter
@Setter
public class MyPointDTO {

    private String uid;

    @Min(value = 0, message = " point 는 음수 불가.")
    private Long point;

    @Max(value = 100_000_000L, message = "Point 최대값 100억.")
    @Min(value = 0, message = "Add point 는 음수 불가.")
    private Long addPoint;

    @Max(value = 100_000_000L, message = "Point 최대값 100억.")
    @Min(value = 0, message = "minus point 는 음수 불가.")
    private Long minusPoint;
    private String nickName;
    private LocalTime insertDateTime;

    @Builder
    public MyPointDTO(String uid, Long point, Long addPoint, Long minusPoint, String nickName, LocalTime insertDateTime) {
        this.uid = uid;
        this.point = point;
        this.addPoint = addPoint;
        this.minusPoint = minusPoint;
        this.nickName = nickName;
        this.insertDateTime = insertDateTime;
    }

    public static MyPointDTO pointFactory(Point point) {
        return MyPointDTO.builder()
                .uid(point.getUid())
                .point(point.getPoint())
                .addPoint(point.getAddPoint())
                .minusPoint(point.getMinusPoint())
                .nickName(point.getNickName())
                .insertDateTime(LocalTime.now())
                .build();
    }
}
