package com.travelproject.travelproject.service.Implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.request.auth.PatchPasswordChangeRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.entity.UserEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.UserRepository;
import com.travelproject.travelproject.service.PasswordChangeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PasswordChangeServiceImplement implements PasswordChangeService {
    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;

    
    @Override
    public ResponseEntity<ResponseDto> changePassword(UserToken userToken, PatchPasswordChangeRequestDto dto) {

        if (userToken == null) return ResponseMessage.NOT_EXIST_USER_TOKEN;

        String userEmail = userToken.getEmail();
        UserEntity user = userRepository.findByEmail(userEmail);

        // 이전 비밀번호 확인
        if (!passwordEncoder.matches(dto.getUserPassword(), user.getPassword())) {
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

