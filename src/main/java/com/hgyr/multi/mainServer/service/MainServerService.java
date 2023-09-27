package com.hgyr.multi.mainServer.service;

import com.hgyr.multi.mainServer.Repository.UserRepository;
import com.hgyr.multi.mainServer.controller.MainController;
import com.hgyr.multi.mainServer.data.Entity.User;
import com.hgyr.multi.mainServer.data.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLDataException;
import java.util.Optional;

@Service
public class MainServerService {

    private final Logger logger = LoggerFactory.getLogger(MainServerService.class);

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
            throw new RuntimeException("[Port:1777] MainController : login 비밀번호가 일치하지 않습니다.");
        }
        return UserDto.entityToDto(user);
    }

    /* 각 서비스에서 userDto 정보 받기 */
    public UserDto findByUId(String uid){
        return UserDto.entityToDto(userRepository.findByUid(uid));
    }

    /* 포인트 정보 조회 */
    public Double getPoint(String uid){
        return userRepository.findByUid(uid).getPoint();
    }

    public String updatePoint(String uid, String point) {
        Double dPoint = Double.parseDouble(point);
        Optional<User> user = Optional.ofNullable(userRepository.findByUid(uid));

            if(user.isPresent()){
                User userInfo = user.get();
                Double currentPoint = userInfo.getPoint();
                Double calPoint = currentPoint + dPoint;
                userInfo.setPoint(calPoint);
                userRepository.save(userInfo);

                Double resultPoint = userRepository.findByUid(uid).getPoint();
                return resultPoint.toString();
            } else {
            logger.info("[Port:1777] MainController : updatePoint Exception ");
            return "error";
        }
    }
}
