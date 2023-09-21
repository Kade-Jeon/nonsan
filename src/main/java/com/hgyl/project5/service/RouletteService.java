package com.hgyl.project5.service;

import com.hgyl.project5.dto.RouletteDTO;
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

    public RouletteDTO roulette(String userId) {

        Roulette roulette = rouletteRepository.findById(userId).orElse(new Roulette());


        RouletteDTO rouletteDTO = RouletteDTO.builder()
                .uid(roulette.getUid())
                .point(roulette.getPoint())
                .nickName(roulette.getNickName())
                .count(roulette.getCount())
                .build();

        return rouletteDTO;
    }

    @Transactional
    public int updatePoint1(String userId) {
        // 기존 포인트 엔티티를 찾거나 새로 생성
        Roulette existingPoint = rouletteRepository.findById(userId).orElse(new Roulette());

        // 현재 포인트를 가져옴
        Long currentPoint = existingPoint.getPoint() != null ? existingPoint.getPoint() : 0;

        // 포인트를 +1000 증가시킴
        Long updatedPoint = currentPoint + 1000;
        existingPoint.setPoint(updatedPoint);

        // 업데이트된 포인트를 반환
        return updatedPoint.intValue();
    }

    @Transactional
    public int updatePoint2(String userId) {
        // 기존 포인트 엔티티를 찾거나 새로 생성
        Roulette existingPoint = rouletteRepository.findById(userId).orElse(new Roulette());

        // 현재 포인트를 가져옴
        Long currentPoint = existingPoint.getPoint() != null ? existingPoint.getPoint() : 0;

        // 포인트를 +1000 증가시킴
        Long updatedPoint = currentPoint + 1000;
        existingPoint.setPoint(updatedPoint);

        // 업데이트된 포인트를 반환
        return updatedPoint.intValue();
    }

    @Transactional
    public int updatePoint3(String userId) {
        // 기존 포인트 엔티티를 찾거나 새로 생성
        Roulette existingPoint = rouletteRepository.findById(userId).orElse(new Roulette());

        // 현재 포인트를 가져옴
        Long currentPoint = existingPoint.getPoint() != null ? existingPoint.getPoint() : 0;

        // 포인트를 +1000 증가시킴
        Long updatedPoint = currentPoint + 1000;
        existingPoint.setPoint(updatedPoint);

        // 업데이트된 포인트를 반환
        return updatedPoint.intValue();
    }

    @Transactional
    public int updatePoint4(String userId) {
        // 기존 포인트 엔티티를 찾거나 새로 생성
        Roulette existingPoint = rouletteRepository.findById(userId).orElse(new Roulette());

        // 현재 포인트를 가져옴
        Long currentPoint = existingPoint.getPoint() != null ? existingPoint.getPoint() : 0;

        // 포인트를 +1000 증가시킴
        Long updatedPoint = currentPoint - 1000;
        existingPoint.setPoint(updatedPoint);

        // 업데이트된 포인트를 반환
        return updatedPoint.intValue();
    }
}