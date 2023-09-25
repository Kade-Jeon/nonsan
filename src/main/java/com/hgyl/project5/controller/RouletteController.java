package com.hgyl.project5.controller;

import com.hgyl.project5.dto.RouletteDTO;
import com.hgyl.project5.dto.UserDto;
import com.hgyl.project5.service.RouletteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@EnableScheduling
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class RouletteController {

    @Autowired
    RouletteService rouletteService;

    private Map<String, String> userList = new HashMap<>();

    //유저 정보 받아오기
    @PostMapping("/room/user/valid")
    @ResponseBody
    public void checkUser(@RequestBody UserDto userDto) throws Exception {
        if(userDto == null){
            throw new Exception();
        }
        userList.put(userDto.getUid(), userDto.getNickName());
    }


    // 룰렛 기본 페이지
    @GetMapping("/minigame/roulette")
    public String roulette(@Valid Model model, HttpSession session) {
        String userId = "uid1";
        session.setAttribute("userId", userId);

        String storedUserId = (String) session.getAttribute("userId");
        if (storedUserId == null) {
            // 세션에 userId가 없을 경우 처리
        }

        RouletteDTO roulette = rouletteService.roulette(userId);

        model.addAttribute("roulette", roulette);

        return "minigame/roulette";
    }

    @PostMapping("/minigame/selectItem")
    public String selectItem(@RequestParam String selectedItem, Model model,  HttpSession session) {

        System.out.println(selectedItem+"이 아이탬 선택됨");
        String userId = "uid1";
        session.setAttribute("userId", userId);

        String storedUserId = (String) session.getAttribute("userId");
        if (storedUserId == null) {
            // 세션에 userId가 없을 경우 처리
        }
        try {
            String responseMessage = "";
            double updatedPoint = 0;

            if ("item1".equals(selectedItem)) {
                updatedPoint = rouletteService.updatePoint1(userId);
                responseMessage = "2배가 선택되어 " + updatedPoint + " 포인트가 지급되었습니다";
            } else if ("item2".equals(selectedItem)) {
                updatedPoint = rouletteService.updatePoint2(userId);
                responseMessage = "포인트 0원 선택되어 " + updatedPoint + " 포인트가 변경되었습니다";
            } else if ("item3".equals(selectedItem)) {
                updatedPoint = rouletteService.updatePoint3(userId);
                responseMessage = "포인트 1.5배 선택되어 " + updatedPoint + " 포인트가 변경되었습니다";
            } else if ("item4".equals(selectedItem)) {
                updatedPoint = rouletteService.updatePoint4(userId);
                responseMessage = "포인트 0.5배 선택되어 " + updatedPoint + " 포인트가 변경되었습니다";
           /* } else if ("item4".equals(selectedItem)) {
                updatedPoint = rouletteService.updatePoint5(userId);
                responseMessage = "포인트 +1만원 선택되어 " + updatedPoint + " 포인트가 변경되었습니다";
            } else if ("item4".equals(selectedItem)) {
                updatedPoint = rouletteService.updatePoint6(userId);
                responseMessage = "포인트 -1만원 선택되어 " + updatedPoint + " 포인트가 변경되었습니다";*/
            } else {
                // 처리 실패 시
                responseMessage = "유효하지 않은 아이템 선택";
                model.addAttribute("message", responseMessage);
                return "redirect:/minigame/roulette";
            }

            // 처리 성공 시 JavaScript를 사용하여 경고창을 띄우고 메시지를 전달
            String script = String.format("alert('%s');", responseMessage);
            model.addAttribute("script", script);
            return "redirect:/minigame/roulette";
        } catch (Exception e) {
            // 오류 처리
            model.addAttribute("message", "서버 오류 발생: " + e.getMessage());
            return "redirect:/minigame/roulette";
        }

/*        int count = 0; // 초기화
        count = rouletteService.resetCount(); // count 초기화
        rouletteService.executeRoulette(userId); // 룰렛 실행

        return "redirect:/minigame/roulette"; */
    }
}