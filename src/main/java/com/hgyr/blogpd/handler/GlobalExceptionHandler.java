package com.hgyr.blogpd.handler;

import com.hgyr.blogpd.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice // 어디에서 예외가 발생하던 이쪽으로 오도록
@RestController
public class GlobalExceptionHandler {

    // IllegalArgumentException 이 발생하면 스프링이 e에 에러를 전달해줌
    // 모든 예외를 처리하고 싶으면 Exception으로 선언
    /*@ExceptionHandler(value = IllegalArgumentException.class)
    public String handleArgumentException(IllegalArgumentException e) {
        return "<h1>" + e.getMessage() + "</h1>";
    }*/

    // 회원가입 기능 구현을 위해 수정
    @ExceptionHandler(value = Exception.class)
    public ResponseDto<String> handleArgumentException(Exception e) {
        // INTERNAL_SERVER_ERROR : 이렇게 보는 것보다는 .value()를 추가해서 상태값을 출력하는 것이 더 좋음
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()); // 500
    }
}