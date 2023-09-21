package com.hgyl.project5.service;

import com.hgyl.project5.dto.MyPointDTO;
import com.hgyl.project5.entity.MyPoint;
import com.hgyl.project5.repository.PointRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PointService {

    private PointRepository pointRepository;

    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    public MyPointDTO myPoint(String userId) {
        MyPoint myPoint = pointRepository.findById(userId).orElse(new MyPoint());

        return MyPointDTO.builder()
                .uid(myPoint.getUid())
                .point(myPoint.getPoint())
                .addPoint(myPoint.getAddPoint())
                .nickName(myPoint.getNickName())
                .build();
    }

    @Transactional
    public void recharge(MyPointDTO myPointDTO) {
        String userId = myPointDTO.getUid();
        if (userId == null) {
            throw new IllegalArgumentException("유저 아이디 Null");
        }

        Long depositAmount = myPointDTO.getAddPoint();
        MyPoint existingPoint = pointRepository.findById(userId).orElse(new MyPoint());

        Long currentPoint = existingPoint.getPoint() != null ? existingPoint.getPoint() : 0;
        existingPoint.setPoint(currentPoint + depositAmount);
    }

    public MyPointDTO deposit(String userId) {
        MyPoint myPoint = pointRepository.findById(userId).orElse(new MyPoint());

        return MyPointDTO.builder()
                .uid(myPoint.getUid())
                .point(myPoint.getPoint())
                .nickName(myPoint.getNickName())
                .build();


    }

    public MyPointDTO withdrawPage(String userId) {
        MyPoint myPoint = pointRepository.findById(userId).orElse(new MyPoint());

        return MyPointDTO.builder()
                .uid(myPoint.getUid())
                .point(myPoint.getPoint())
                .minusPoint(myPoint.getMinusPoint())
                .nickName(myPoint.getNickName())
                .build();
    }

    @Transactional
    public void withdraw(MyPointDTO myPointDTO) {
        String userId = myPointDTO.getUid();
        Long depositAmount = myPointDTO.getMinusPoint();

        // 기존 포인트 엔티티를 찾거나 새로 생성
        MyPoint existingPoint = pointRepository.findById(userId).orElse(new MyPoint());

        // 기존 포인트에 포인트 마이너스
        Long currentPoint = existingPoint.getPoint() != null ? existingPoint.getPoint() : 0;
        existingPoint.setPoint(currentPoint - depositAmount);

    }


    public Long currentPoint(String userId) {
        MyPoint myPoint = pointRepository.findById(userId).orElse(new MyPoint());
        return myPoint.getPoint(); // 사용자의 현재 포인트 반환
    }
}


