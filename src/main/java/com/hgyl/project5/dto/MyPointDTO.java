package com.hgyl.project5.dto;

import com.hgyl.project5.entity.Point;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

@NoArgsConstructor
@Getter
@Setter
public class MyPointDTO {

    private Integer id;
    private String uid;
    private Integer point;
    private Integer addPoint;
    private Integer minusPoint;
    private String nickName;


    public MyPointDTO mypoint(Point point) {
        this.point = point.getPoint();
        this.addPoint = point.getAddPoint();
        this.id = point.getId();
        this.minusPoint=point.getMinusPoint();
        this.nickName=point.getNickName();
        this.uid=point.getUid();

        return this;
    }

    public static MyPointDTO pointFactory(Point point) {
        MyPointDTO myPointDTO = new MyPointDTO();
        return myPointDTO.mypoint(point);
    }
}
