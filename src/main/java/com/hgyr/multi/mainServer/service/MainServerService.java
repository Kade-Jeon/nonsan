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
    
    /* 전달받은 userDto를 이용하여
    * 비밀번호는 암호화하여 DB에 저장
    * 실패하면 문자열 error리턴
    * 성공하면 success 리턴*/
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

    /* 전달받은 userDto(아이디/비밀번호)를 이용하여 로그인 시도
     * 전달받은 비밀번호가 암호화된 비밀번호와 일치하는지 검증
     * 실패하면 RuntimeException 던짐
     * 성공하면 userDto 리턴*/
    public UserDto login(UserDto userDto) throws RuntimeException{
        User user = userRepository.findByUid(userDto.getUid());
        if(!passwordEncoder.matches(userDto.getPassword(), user.getPassword())){
            System.out.println("비밀번호가 다름");
            throw new RuntimeException();
        }
        return UserDto.entityToDto(user);
    }

    /* 각 서비스에서 userDto 정보 받기 */
    public UserDto findByUId(String uId){
        return UserDto.entityToDto(userRepository.findByUid(uId));
    }
}
