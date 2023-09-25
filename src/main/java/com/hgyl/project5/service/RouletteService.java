package com.hgyl.project5.service;

import com.hgyl.project5.dto.RouletteDTO;
import com.hgyl.project5.entity.MyPoint;
import com.hgyl.project5.entity.Roulette;
import com.hgyl.project5.repository.RouletteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RouletteService {

    private RouletteRepository rouletteRepository;

    @Autowired
    public RouletteService(RouletteRepository rouletteRepository) {
        this.rouletteRepository = rouletteRepository;
    }

    @Transactional
    public RouletteDTO roulette(String userId) {

        Roulette roulette = rouletteRepository.findById(userId).orElse(new Roulette());
        MyPoint myPoint = roulette.getMyPoint();

        RouletteDTO rouletteDTO = RouletteDTO.builder()
                .uid(roulette.getUid())
                .point(myPoint.getPoint())
                .nickName(myPoint.getNickName())
                .count(roulette.getCount())
                .build();

        return rouletteDTO;
    }

    // 포인트 0.5배
    @Transactional
    public int updatePoint1(String userId) {
        // 기존 포인트 엔티티를 찾거나 새로 생성
        Roulette existingPoint = rouletteRepository.findById(userId).orElse(new Roulette());
        MyPoint myPoint = existingPoint.getMyPoint();

        // 현재 포인트를 가져옴
        Double currentPoint = myPoint.getPoint() != null ? myPoint.getPoint() : 0;

        // 포인트를 2배 증가
        Double updatedPoint = currentPoint * 2;
        myPoint.setPoint(updatedPoint);

        // 업데이트된 포인트를 반환
        return updatedPoint.intValue();
    }

    // 포인트 0원
    @Transactional
    public int updatePoint2(String userId) {
        // 기존 포인트 엔티티를 찾거나 새로 생성
        Roulette existingPoint = rouletteRepository.findById(userId).orElse(new Roulette());
        MyPoint myPoint = existingPoint.getMyPoint();

        // currentPoint를 항상 0으로 설정
        Double currentPoint = 0.0;

        Double updatedPoint = currentPoint;

        myPoint.setPoint(updatedPoint);

        // 업데이트된 포인트를 반환
        return updatedPoint.intValue();
    }

    // 포인트 1.5배
    @Transactional
    public int updatePoint3(String userId) {
        // 기존 포인트 엔티티를 찾거나 새로 생성
        Roulette existingPoint = rouletteRepository.findById(userId).orElse(new Roulette());
        MyPoint myPoint = existingPoint.getMyPoint();

        // 현재 포인트를 가져옴
        Double currentPoint = myPoint.getPoint() != null ? myPoint.getPoint() : 0;

        Double updatedPoint = currentPoint * 1.5;
        myPoint.setPoint(updatedPoint);

        // 업데이트된 포인트를 반환
        return updatedPoint.intValue();
    }

    // 포인트 0.5배
    @Transactional
    public int updatePoint4(String userId) {
        // 기존 포인트 엔티티를 찾거나 새로 생성
        Roulette existingPoint = rouletteRepository.findById(userId).orElse(new Roulette());
        MyPoint myPoint = existingPoint.getMyPoint();

        // 현재 포인트를 가져옴
        Double currentPoint = myPoint.getPoint() != null ? myPoint.getPoint() : 0;

        Double updatedPoint = currentPoint * 0.5;
        myPoint.setPoint(updatedPoint);

        // 업데이트된 포인트를 반환
        return updatedPoint.intValue();
    }

    public int resetCount() {
        return 0;
    }

    public void executeRoulette(String userId) {

    }

    /*   *//*포인트 +1만원*//*
    @Transactional
    public int updatePoint5(String userId) {
        // 기존 포인트 엔티티를 찾거나 새로 생성
        Roulette existingPoint = rouletteRepository.findById(userId).orElse(new Roulette());

        // 현재 포인트를 가져옴
        Double currentPoint = existingPoint.getPoint() != null ? existingPoint.getPoint() : 0;

        // 포인트를 +1000 증가시킴
        Double updatedPoint = currentPoint + 10000;
        existingPoint.setPoint(updatedPoint);

        // 업데이트된 포인트를 반환
        return updatedPoint.intValue();
    }

    *//*포인트 -1만원*//*
    @Transactional
    public int updatePoint6(String userId) {
        // 기존 포인트 엔티티를 찾거나 새로 생성
        Roulette existingPoint = rouletteRepository.findById(userId).orElse(new Roulette());

        // 현재 포인트를 가져옴
        Double currentPoint = existingPoint.getPoint() != null ? existingPoint.getPoint() : 0;

        // 포인트를 +1000 증가시킴
        Double updatedPoint = currentPoint - 10000;
        existingPoint.setPoint(updatedPoint);

        // 업데이트된 포인트를 반환
        return updatedPoint.intValue();
    }*/
}