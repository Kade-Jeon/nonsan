package com.hgyl.project5.controller;

import com.hgyl.project5.dto.RouletteDTO;
import com.hgyl.project5.service.RouletteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RouletteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RouletteService rouletteService;

    @Test
    public void testRoulette() throws Exception {
        // 테스트 데이터 생성
        String userId = "uid1";

        // 컨트롤러 메서드 호출 및 응답 검증
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/minigame/roulette"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("minigame/roulette"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("roulette"))
                .andReturn();

        // 결과 검증
        RouletteDTO rouletteDTO = (RouletteDTO) result.getModelAndView().getModel().get("roulette");
        assertNotNull(rouletteDTO);
        assertEquals(userId, rouletteDTO.getUid());
    }
}