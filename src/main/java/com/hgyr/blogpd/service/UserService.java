package com.hgyr.blogpd.service;

import com.hgyr.blogpd.model.RoleType;
import com.hgyr.blogpd.model.User;
import com.hgyr.blogpd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록 -> IoC를 해줌(메모리에 대신 띄움)
@Service
public class UserService {

    @Autowired // DI
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder encoder;

    @Transactional // 전체가 성공하면 commit, 실패하면 rollback
    public void 회원가입(User user) {
        String rawPassword = user.getPassword(); // 1234 원문
//        String encPassword = encoder.encode(rawPassword); // 해쉬화
        String encPassword = "1234"; // 해쉬화
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    /*@Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료 시에 트랜잭션 종료 (정합성 유지)
    public User 로그인(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/
}