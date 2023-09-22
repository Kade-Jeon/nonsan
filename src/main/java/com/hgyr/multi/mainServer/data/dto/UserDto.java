package com.hgyr.multi.mainServer.data.dto;

import com.hgyr.multi.mainServer.data.Entity.User;
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
    private Date birthDate;
    private Double point;
    private LocalDateTime joinDate;
    private LocalDateTime quitDate;

    public UserDto fromEntity(User user){
        this.id = user.getId();
        this.uid = user.getUid();
        this.nickName = user.getNickName();
        this.password = user.getPassword();
        this.name = user.getName();
        this.birthDate = user.getBirthDate();
        this.point = user.getPoint();
        this.joinDate = user.getJoinDate();
        this.quitDate = user.getQuitDate();
        return this;
    }

    public static UserDto entityToDto (User user){
        UserDto userDto = new UserDto();
        userDto.fromEntity(user);
        return userDto;
    }
}

