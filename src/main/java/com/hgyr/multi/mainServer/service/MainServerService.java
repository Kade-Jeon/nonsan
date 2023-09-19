package com.hgyr.multi.mainServer.service;

import com.hgyr.multi.mainServer.Repository.UserRepository;
import com.hgyr.multi.mainServer.data.Entity.User;
import com.hgyr.multi.mainServer.data.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MainServerService {


    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDto userDto;

    public MainServerService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String save(UserDto userDto) {
        String codedPwd = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(codedPwd);
        try{
            userRepository.save(User.dtoToEntity(userDto));
        }catch (Exception e){
            return "error";
        }
        return "success";
    }

    public UserDto login(UserDto userDto) throws RuntimeException{
        User user = userRepository.findByUid(userDto.getUid());
        if(!passwordEncoder.matches(userDto.getPassword(), user.getPassword())){
            System.out.println("비밀번호가 다름");
            throw new RuntimeException();
        }
        return UserDto.entityToDto(user);
    }

    /*유저 아이디 던지면 잘 넘어오는지 테스트*/
    public UserDto findByUId(String uId){
        return UserDto.entityToDto(userRepository.findByUid(uId));
    }
}
