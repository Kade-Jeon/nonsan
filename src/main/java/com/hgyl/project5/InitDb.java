package com.hgyl.project5;

import com.hgyl.project5.controller.PointController;
import com.hgyl.project5.entity.MyPoint;
import com.hgyl.project5.entity.Roulette;
import com.hgyl.project5.repository.PointRepository;
import com.hgyl.project5.repository.RouletteRepository;
import com.hgyl.project5.service.PointService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Transactional
@RequiredArgsConstructor
@Component
public class InitDb {

    private final PointRepository pointRepository;
    private final RouletteRepository rouletteRepository;

    @PostConstruct
    public void init() {
        MyPoint myPoint = new MyPoint();
        myPoint.setUid("uid1");
        myPoint.setPoint(10000.0);
        myPoint.setNickName("준형ol");

        Roulette roulette = new Roulette();
        roulette.setUid("uid1");
        roulette.setCount(10);
        roulette.setMyPoint(myPoint);

        pointRepository.save(myPoint);
        rouletteRepository.save(roulette);
    }
}
