package com.hgyl.nonsan_message.data.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 로그인 사용자 불러오기 위한 임시 entity
 * uid : 식별 값
 * password : 비밀번호
 * userName : 아이디
 * age : 게임 접근 제한을 위한 나이 정보
 * point : 적립된 포인트 값
 *
 * **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private int age;

    @Column
    private int point;

}
