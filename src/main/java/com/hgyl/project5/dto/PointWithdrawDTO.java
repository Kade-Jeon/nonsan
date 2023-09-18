package com.hgyl.project5.dto;

import com.hgyl.project5.entity.Point;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PointWithdrawDTO {
    private Integer id;
    private String uid;
    private Integer point;
    private Integer addPoint;
    private Integer minusPoint;
    private String nickName;

    public PointWithdrawDTO fromPoint(Point point) {
        this.point = point.getPoint();
        this.addPoint = point.getAddPoint();
        this.id = point.getId();
        this.minusPoint=point.getMinusPoint();
        this.nickName=point.getNickName();
        this.uid=point.getUid();

        return this;
    }

    public static PointWithdrawDTO pointFactory(Point point) {
        PointWithdrawDTO pointWithdrawDTO = new PointWithdrawDTO();
        return pointWithdrawDTO.fromPoint(point);
    }
}
