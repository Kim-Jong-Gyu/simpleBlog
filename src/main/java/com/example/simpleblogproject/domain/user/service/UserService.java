package com.example.simpleblogproject.domain.user.service;


import com.example.simpleblogproject.domain.redis.RedisUtil;
import com.example.simpleblogproject.domain.user.dto.SignupRequestDto;
import com.example.simpleblogproject.domain.user.entity.User;
import com.example.simpleblogproject.domain.user.repository.UserRepository;
import com.example.simpleblogproject.global.common.CommonResponseCode;
import com.example.simpleblogproject.global.common.CommonResponse;
import com.example.simpleblogproject.global.exception.CustomException;
import com.example.simpleblogproject.global.exception.ExceptionResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RedisUtil redisUtil;

    public CommonResponse signup(SignupRequestDto requestDto) {
        String nickname = requestDto.getNickname();
        String password = requestDto.getPassword();
        String email = requestDto.getEmail();
        if(password.contains(nickname)){
            throw new CustomException(ExceptionResponseCode.PASSWORD_CONTAIN_NICKNAME);
        }
        if(!password.equals(requestDto.getCheckPassword())){
            throw new CustomException(ExceptionResponseCode.PASSWORD_MACH_CHECKPASSWORD);
        }
        if(!redisUtil.getData(requestDto.getAuthNum()).equals(requestDto.getEmail())){
            throw new CustomException(ExceptionResponseCode.EMAIL_CODE_NOT_MATCH);
        }
        User user = User.builder()
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .email(email).build();
        userRepository.save(user);
        return new CommonResponse(CommonResponseCode.USER_CREATE);
    }


    public CommonResponse validateDuplicateUser(String nickname){
        userRepository.findByNickname(nickname).ifPresent(m -> {
            throw new CustomException(ExceptionResponseCode.NICKNAME_DUPLICATE);
        });
        return new CommonResponse(CommonResponseCode.USER_NICKNAME_OK);
    }
}
