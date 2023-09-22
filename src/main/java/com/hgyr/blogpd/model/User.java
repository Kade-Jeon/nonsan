package com.hgyr.blogpd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 30, unique = true)
    private String uid; // id

    @Column(nullable = false, length = 30, unique = true)
    private String nickname; // 닉네임

    @Column(nullable = false, length = 100) // 추후 해쉬(비밀번호 암호화) 변경을 위해 길이를 넉넉히 설정
    private String password;

    @Column(nullable = false, length = 50)
    private String email;
    
    @Enumerated(EnumType.STRING) // DB는 RoleType이 없기 때문에 어노테이션을 붙여서 String 타입이라는 것을 알려줘야 함
    private RoleType role; // 권한

    @CreationTimestamp
    private Timestamp joinDate; // 가입일시
}