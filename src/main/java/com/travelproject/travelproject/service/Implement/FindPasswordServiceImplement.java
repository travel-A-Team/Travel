package com.travelproject.travelproject.service.Implement;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.dto.response.auth.FindPasswordResponseDto;
import com.travelproject.travelproject.entity.UserEntity;
import com.travelproject.travelproject.repository.UserRepository;
import com.travelproject.travelproject.service.FindPasswordService;

@Service
public class FindPasswordServiceImplement implements FindPasswordService {
    
    private UserRepository userRepository;
    private JavaMailSender javaMailSender;
    
    @Autowired
    public FindPasswordServiceImplement(UserRepository userRepository, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    @Override
    public FindPasswordResponseDto resetPassword(String email, String phoneNumber) {
        UserEntity user = userRepository.findByEmailAndPhoneNumber(email, phoneNumber);

        if (user == null) {
            return new FindPasswordResponseDto(false, "존재하지 않는 사용자입니다.", null);
        }

        //* 임시 비밀번호 생성 후 저장
        String temporaryPassword = generateTemporaryPassword();
        user.setPassword(encryptPassword(temporaryPassword));
        userRepository.save(user);

        //* 임시 비밀번호 메일로 전송
        sendEmailWithTemporaryPassword(email, temporaryPassword);

        return new FindPasswordResponseDto(true, "임시 비밀번호를 메일로 전송했습니다.", temporaryPassword);
    }

    //* 임시 비밀번호 난수 
    private String generateTemporaryPassword() {
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }

    //* 비밀번호 암호화
    private String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    //* 임시 비밀번호 전송
    private void sendEmailWithTemporaryPassword(String email, String temporaryPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("example@naver.com");
        message.setTo(email);
        message.setSubject("임시 비밀번호 발급");
        message.setText("회원님의 임시 비밀번호는 " + temporaryPassword + " 입니다. \n로그인 후 비밀번호를 변경해주세요.");

        javaMailSender.send(message);
    }
}

