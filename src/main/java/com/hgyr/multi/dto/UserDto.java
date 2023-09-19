package com.hgyr.multi.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Component
public class UserDto {

    private Long id;
    private String uid;
    private String nickName;
    private String password;
    private String name;
    private Date date;
    private Integer age;
    private Integer point;
    private LocalDateTime joinDate;
    private LocalDateTime quitDate;

}

