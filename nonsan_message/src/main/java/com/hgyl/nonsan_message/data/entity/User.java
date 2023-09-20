package com.hgyl.nonsan_message.data.entity;


import lombok.*;


import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;


/**
 * 로그인 사용자 불러오기 위한 임시 entity
 * uid : 식별 값
 * password : 비밀번호
 * userName : 아이디
 * age : 게임 접근 제한을 위한 나이 정보
 * point : 적립된 포인트 값
 *
 * **/

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

    /* null 허용 x, update 허용(수정불가)
     *  "", " " 허용 x
     * 양수만 허용
     * 나이 최소값 0
     * */
    @Column(nullable = false)
    @PositiveOrZero
    @Min(value = 0)
    private Integer age;

    @Column(nullable = false)
    @PositiveOrZero
    @Min(value = 0)
    private Integer point;

    /*public User fromDto(UserDto userDto){
        this.uid = userDto.getUid();
        this.nickName = userDto.getNickName();
        this.password = userDto.getPassword();
        this.name = userDto.getName();
        this.age = userDto.getAge();
        this.point = userDto.getPoint();
        return this;
    }

    public static User dtoToEntity(@Valid UserDto userDto) {
        User user = new User();
        user.fromDto(userDto);
        return user;
    }*/
}