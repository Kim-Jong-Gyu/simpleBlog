package com.example.simpleblogproject.domain.user.service;


import com.example.simpleblogproject.domain.user.dto.SignupRequestDto;
import com.example.simpleblogproject.domain.user.entity.User;
import com.example.simpleblogproject.domain.user.repository.UserRepository;
import com.example.simpleblogproject.global.CommonResponseCode;
import com.example.simpleblogproject.global.CommonResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public CommonResponseDto signup(SignupRequestDto requestDto) {
        String nickname = requestDto.getNickname();
        String password = requestDto.getPassword();
        String email = requestDto.getEmail();
        if(password.contains(nickname)){
            throw new IllegalArgumentException("비밀번호가 닉네임을 포함하고 있습니다.");
        }
        if(!password.equals(requestDto.getCheckPassword())){
            throw new IllegalArgumentException("입력한 비밀번호가 비밀번호 확인가 다릅니다.");
        }
        validateDuplicateUser(nickname);
        User user = User.builder()
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .email(email).build();
        userRepository.save(user);
        return new CommonResponseDto(CommonResponseCode.USER_CREATE);
    }


    private void validateDuplicateUser(String nickname){
        userRepository.findByNickname(nickname).ifPresent(m -> {
            throw new IllegalArgumentException("중복된 닉네임입니다.");
        });
    }
}
