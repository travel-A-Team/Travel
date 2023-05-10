package com.travelproject.travelproject.service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.request.adminMain.SignInRequestDto;
import com.travelproject.travelproject.dto.response.adminMain.SignInResponseDto;
import com.travelproject.travelproject.provider.JwtTokenProvider;
import com.travelproject.travelproject.service.AdminMainService;


@Service
public class AdminMainServiceImplement implements AdminMainService {
    
    @Value("${admin.email}")
    String email;
    @Value("${admin.password}")
    String password;

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AdminMainServiceImplement(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        SignInResponseDto body = null;
        String adminEamil = dto.getAdminEmail();
        String adminPassword = dto.getAdminPassword();

            // TODO: 로그인 실패 반환 (이메일X)
            if (!adminEamil.equals(email)) return ResponseMessage.SIGN_IN_FAILED;

            // TODO: 로그인 실패 반환 (패스워드X)
            if (!adminPassword.equals(adminPassword)) return ResponseMessage.SIGN_IN_FAILED;

            // TODO: 토큰 생성
            String role = "admin";
            String jwt = jwtTokenProvider.create(adminEamil, role);
            System.out.println("jwt 값: " + jwt);
            body = new SignInResponseDto(jwt);

        //* 성공
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
}
