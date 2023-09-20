package com.hgyl.project5.controller;

import com.hgyl.project5.dto.RouletteDTO;
import com.hgyl.project5.entity.Roulette;
import com.hgyl.project5.service.PointService;
import com.hgyl.project5.service.RouletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

@Controller
public class RouletteController {

    @Autowired
    RouletteService rouletteService;

    @GetMapping("/minigame/roulette")
    public String roulette(@Valid Model model){
        String userId = "uid1";
        RouletteDTO roulette = rouletteService.roulette(userId);

        model.addAttribute("roulette", roulette);

        return "minigame/roulette";

    }
}
