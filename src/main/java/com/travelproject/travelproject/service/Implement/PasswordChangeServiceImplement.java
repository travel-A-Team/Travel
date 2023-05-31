package com.travelproject.travelproject.service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.request.auth.PatchPasswordChangeRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.UserEntity;
import com.travelproject.travelproject.repository.UserRepository;
import com.travelproject.travelproject.service.PasswordChangeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PasswordChangeServiceImplement implements PasswordChangeService {
    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    
    @Override
    public ResponseEntity<ResponseDto> changePassword(String email, PatchPasswordChangeRequestDto dto) {

        // 사용자 이메일을 기반으로 UserEntity를 조회
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            return ResponseMessage.NOT_EXIST_USER_EMAIL;
        }

        // 이전 비밀번호 확인
        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            return ResponseMessage.NOT_EQUAL_PASSWORD;
        }

        // 새로운 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (!dto.getNewPassword().equals(dto.getNewPasswordCheck())) {
            return ResponseMessage.PASSWORDCHECK_ERROR;
        }

        // 비밀번호 업데이트
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        userRepository.save(user);

        
        return ResponseMessage.SUCCESS;
    }
}

