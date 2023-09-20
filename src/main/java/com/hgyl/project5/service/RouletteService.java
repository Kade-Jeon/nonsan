package com.hgyl.project5.service;

import com.hgyl.project5.dto.RouletteDTO;
import com.hgyl.project5.entity.Roulette;
import com.hgyl.project5.repository.RouletteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RouletteService {

    private RouletteRepository rouletteRepository;

    @Autowired
    public RouletteService(RouletteRepository rouletteRepository) {
        this.rouletteRepository = rouletteRepository;
    }

    public RouletteDTO roulette(String userId) {
        // RouletteRepository에서 데이터 조회 로직을 구현하고 RouletteDTO로 변환
        Roulette roulette = rouletteRepository.findById(userId).orElse(new Roulette());

        // Roulette 객체를 RouletteDTO로 변환
        RouletteDTO rouletteDTO = RouletteDTO.builder()
                .uid(roulette.getUid())
                .point(roulette.getPoint())
                .nickName(roulette.getNickName())
                .count(roulette.getCount())
                .build();

        return rouletteDTO;
    }
}
