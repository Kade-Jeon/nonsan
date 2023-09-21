package com.hgyr.multi.service;

import com.hgyr.multi.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;

@Service
public class MainRestService {

    /*회원가입을 서버로 요청
    * 서버 포트 1777
    * 상세경로 /valid/join
    * 요청정보(uri)와 userDto를 전송하여 String으로 전송결과 리턴 받아서
    * 컨트롤러로 String결과 리턴
    * */
    public String postSave(UserDto userDto){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:1777")
                .path("/valid/join")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(uri, userDto, String.class);
        return responseEntity.getBody();
    }


    /*회원가입을 서버로 요청
     * 서버 포트 1777
     * 상세경로 /valid/login
     * 요청정보(uri)와 userDto(아이디/비밀번호)를 전송하여 UserDto로 전송결과 리턴 받아서
     * 컨트롤러로 UserDto결과 리턴
     * */
    public UserDto login(UserDto userDto){

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:1777")
                .path("/valid/login")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserDto> responseEntity = restTemplate.postForEntity(uri, userDto, UserDto.class);
        return responseEntity.getBody();
    }
}
