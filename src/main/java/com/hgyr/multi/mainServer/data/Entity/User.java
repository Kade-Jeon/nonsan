package com.hgyr.multi.mainServer.data.Entity;

import com.hgyr.multi.mainServer.data.dto.UserDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.sql.Date;

@Entity
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity{

    /* Primiary key
     * Auto_Increment
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* null 허용 x, update 허용(수정불가)
    *  "", " " 허용 x
    * 아이디 길이 최소 5 ~ 최대 50
    * */
    @Column(nullable = false, updatable = false)
    @NotBlank
    @Size(min = 4, max = 50)
    private String uid;

    /* null 허용 x
     *  "", " " 허용 x
     * */
    @Column(nullable = false)
    @NotBlank
    private String nickName;

    @Column(nullable = false)
    @NotBlank
    private String password;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    private Date birthDate;

    @Column(nullable = false)
    @PositiveOrZero
    @Min(value = 0)
    private Integer point;

    public User fromDto(UserDto userDto){
        this.uid = userDto.getUid();
        this.nickName = userDto.getNickName();
        this.password = userDto.getPassword();
        this.name = userDto.getName();
        this.birthDate = userDto.getBirthDate();
        this.point = userDto.getPoint();
        return this;
    }

    public static User dtoToEntity(@Valid UserDto userDto) {
        User user = new User();
        user.fromDto(userDto);
        return user;
    }
}
