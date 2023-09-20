package com.hgyl.project5.service;

import com.hgyl.project5.dto.MyPointDTO;
import com.hgyl.project5.entity.Point;
import com.hgyl.project5.repository.PointRepository;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    private PointRepository pointRepository;

    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public MyPointDTO myPoint(String userId) {
        Point point = pointRepository.findById(userId).orElse(new Point());

        return MyPointDTO.builder()
                .uid(point.getUid())
                .point(point.getPoint())
                .addPoint(point.getAddPoint())
                .nickName(point.getNickName())
                .build();
    }

    public void recharge(MyPointDTO myPointDTO) {
        String userId = myPointDTO.getUid();
        if (userId == null) {
            throw new IllegalArgumentException("유저 아이디 Null");
        }

        Long depositAmount = myPointDTO.getAddPoint();
        Point existingPoint = pointRepository.findById(userId).orElse(new Point());

        Long currentPoint = existingPoint.getPoint() != null ? existingPoint.getPoint() : 0;
        existingPoint.setPoint(currentPoint + depositAmount);

        pointRepository.save(existingPoint);
    }

    public MyPointDTO deposit(String userId) {
        Point point = pointRepository.findById(userId).orElse(new Point());

        return MyPointDTO.builder()
                .uid(point.getUid())
                .point(point.getPoint())
                .nickName(point.getNickName())
                .build();


    }

    public MyPointDTO withdrawPage(String userId) {
        Point point = pointRepository.findById(userId).orElse(new Point());

        return MyPointDTO.builder()
                .uid(point.getUid())
                .point(point.getPoint())
                .minusPoint(point.getMinusPoint())
                .nickName(point.getNickName())
                .build();
    }

    public void withdraw(MyPointDTO myPointDTO) {
        String userId = myPointDTO.getUid();
        Long depositAmount = myPointDTO.getMinusPoint();

        // 기존 포인트 엔티티를 찾거나 새로 생성
        Point existingPoint = pointRepository.findById(userId).orElse(new Point());

        // 기존 포인트에 포인트 마이너스
        Long currentPoint = existingPoint.getPoint() != null ? existingPoint.getPoint() : 0;
        existingPoint.setPoint(currentPoint - depositAmount);

        // 엔티티 저장
        pointRepository.save(existingPoint);
    }


    public Long currentPoint(String userId) {
        Point point = pointRepository.findById(userId).orElse(new Point());
        return point.getPoint(); // 사용자의 현재 포인트 반환
    }
}


