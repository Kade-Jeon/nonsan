package com.hgyr.multi.mainServer.controller;

import com.hgyr.multi.mainServer.data.dto.UserDto;
import com.hgyr.multi.mainServer.service.MainServerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;

@RestController
@RequestMapping("/valid")
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    MainServerService mainServerService;

    @Autowired
    public MainController(MainServerService mainServerService) {
        this.mainServerService = mainServerService;
    }

    /*main으로부터 정보 받아 회원가입 처리*/
    @PostMapping("/join")
    public ResponseEntity<String> userJoin(@RequestBody UserDto userDto){
        logger.info("[Port:1777] MainController : 회원가입 " + userDto);
        String result = mainServerService.save(userDto);
        if (result.equals("error")){
            return ResponseEntity.status(HttpStatus.OK).body("error");
        }
        return ResponseEntity.status(HttpStatus.OK).body("main");
    }
    /*main으로부터 정보 받아 로그인 후, dto리턴*/
    @PostMapping("/login")
    public ResponseEntity<UserDto> userLogin(@RequestBody UserDto userDto){
        logger.info("[Port:1777] MainController : 로그인 " + userDto);
        UserDto checkedUser = mainServerService.login(userDto);
        return ResponseEntity.status(HttpStatus.OK).body(checkedUser);
    }

    /*각 서비스에서 uid를 통해 요청하면 dto리턴*/
    @PostMapping("/user/{uid}")
    public ResponseEntity<UserDto> userInfo(@PathVariable String uid){
        logger.info("[Port:1777] MainController : userInfo " + uid);
        UserDto info = mainServerService.findByUId(uid);
        return ResponseEntity.status(HttpStatus.OK).body(info);
    }

    /* 게임 & 베팅 : 포인트 조회*/
    @PostMapping("/point/{uid}")
    public ResponseEntity<Double> getPoint(@PathVariable String uid){
        logger.info("[Port:1777] MainController : getPoint " + uid);
        Double point = mainServerService.getPoint(uid);
        return ResponseEntity.status(HttpStatus.OK).body(point);
    }

    @PostMapping("/point/{uid}/{point}")
    ResponseEntity<String> updatePoint(@PathVariable("uid") String uid, @PathVariable("point") String point) {
           logger.info("[Port:1777] MainController : updatePoint " + uid + point);
          String updatedPoint = mainServerService.updatePoint(uid, point);
          if(updatedPoint == null){
              return ResponseEntity.status(HttpStatus.OK).body("[Port:1777] MainController : updatePoint : Data update Error");
          }
        return ResponseEntity.status(HttpStatus.OK).body(updatedPoint);
    }


}
