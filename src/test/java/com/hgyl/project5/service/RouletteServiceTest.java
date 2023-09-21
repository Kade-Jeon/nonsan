package com.hgyl.project5.service;

import com.hgyl.project5.dto.RouletteDTO;
import com.hgyl.project5.entity.Roulette;
import com.hgyl.project5.repository.RouletteRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RouletteServiceTest {

    @Autowired
    private RouletteService rouletteService;

    @Test
    public void testRoulette() {
        // 테스트 데이터 생성
        String userId = "uid1";
        Roulette roulette = new Roulette();
        roulette.setUid(userId);
        roulette.setPoint(10000L);
        roulette.setNickName("준형ol");
        roulette.setCount(2);

        // 테스트 대상 메서드 호출
        RouletteDTO rouletteDTO = rouletteService.roulette(userId);

        // 결과 검증
        assertNotNull(rouletteDTO);
        assertEquals(userId, rouletteDTO.getUid());
        assertEquals(10000L, rouletteDTO.getPoint().longValue());
        assertEquals("준형ol", rouletteDTO.getNickName());
        assertEquals(2, rouletteDTO.getCount());
    }
}