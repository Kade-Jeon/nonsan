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

        MyPointDTO myPointDTO = new MyPointDTO();
        myPointDTO.setUid(point.getUid());
        myPointDTO.setPoint(point.getPoint());
        myPointDTO.setAddPoint(point.getAddPoint());
        myPointDTO.setNickName(point.getNickName());

        return myPointDTO;
    }

    public void recharge(MyPointDTO myPointDTO) {

        String userId = myPointDTO.getUid();
        Long depositAmount = myPointDTO.getAddPoint();

        // 기존 포인트 엔티티를 찾거나 새로 생성
        Point existingPoint = pointRepository.findById(userId).orElse(new Point());

        // 기존 포인트에 추가 포인트 더하기
        // 기존 포인트가 'null' 이라면 기본값 0으로 설정
        Long currentPoint = existingPoint.getPoint() != null ? existingPoint.getPoint() : 0;
        existingPoint.setPoint(currentPoint + depositAmount);

        // 엔티티 저장
        pointRepository.save(existingPoint);
    }

    public MyPointDTO deposit(String userId) {
        Point point = pointRepository.findById(userId).orElse(new Point());

        MyPointDTO myPointDTO = new MyPointDTO();
        myPointDTO.setUid(point.getUid());
        myPointDTO.setPoint(point.getPoint());
        myPointDTO.setNickName(point.getNickName());

        return myPointDTO;
    }

    public MyPointDTO withdrawPage(String userId) {
        Point point = pointRepository.findById(userId).orElse(new Point());

        MyPointDTO myPointDTO = new MyPointDTO();
        myPointDTO.setUid(point.getUid());
        myPointDTO.setPoint(point.getPoint());
        myPointDTO.setMinusPoint(point.getAddPoint());
        myPointDTO.setNickName(point.getNickName());

        return myPointDTO;
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


