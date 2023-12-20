package com.example.simpleblogproject.domain.email.service;

import com.example.simpleblogproject.domain.email.dto.EmailRequestDto;
import com.example.simpleblogproject.domain.redis.RedisUtil;
import com.example.simpleblogproject.global.common.CommonResponse;
import com.example.simpleblogproject.global.common.CommonResponseCode;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final RedisUtil redisUtil;

    @Value("${spring.mail.username}")
    private String username;

    private int authNumber;


    public CommonResponse send(EmailRequestDto requestDto) {
        makeRandomNumber();
        String setFrom = username;
        String toMail = requestDto.getEmail();
        String title = "회원 가입 인증 이메일 입니다.";
        String content = "인증 번호는 " + authNumber + "입니다.";
        mailSend(setFrom, toMail, title, content);
        return new CommonResponse(CommonResponseCode.SEND_EMAIL_SUCCESS);
    }

    private void mailSend(String setFrom, String toMail, String title, String content){
        MimeMessage message = mailSender.createMimeMessage();//JavaMailSender 객체를 사용하여 MimeMessage 객체를 생성
            //이메일 메시지와 관련된 설정을 수행
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,true,"utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            helper.setText(content,true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        redisUtil.setDataExpire(Integer.toString(authNumber), toMail, 60 * 5L);
    }

    private void makeRandomNumber() {
        Random r = new Random();
        StringBuilder randomNumber = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            randomNumber.append(Integer.toString(r.nextInt(10)));
        }
        authNumber = Integer.parseInt(String.valueOf(randomNumber));
    }
}
