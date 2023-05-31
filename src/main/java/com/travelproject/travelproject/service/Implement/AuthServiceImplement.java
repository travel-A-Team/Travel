package com.travelproject.travelproject.service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.dto.request.auth.GetFindEmailRequestDto;
import com.travelproject.travelproject.dto.request.auth.SignInRequestDto;
import com.travelproject.travelproject.dto.request.auth.SignUpRequestDto;
import com.travelproject.travelproject.dto.response.ResponseDto;
import com.travelproject.travelproject.dto.response.auth.GetFindEmailResponseDto;
import com.travelproject.travelproject.dto.response.auth.SignInResponseDto;
import com.travelproject.travelproject.entity.UserEntity;
import com.travelproject.travelproject.provider.JwtTokenProvider;
import com.travelproject.travelproject.repository.UserRepository;
import com.travelproject.travelproject.service.AuthService;


@Service
public class AuthServiceImplement implements AuthService{

    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${admin.email}")
    String adminEmail;
    @Value("${admin.password}")
    String adminPassword;
    
    @Autowired
    public AuthServiceImplement(UserRepository userRepository, JwtTokenProvider jwtTokenProvider){
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    
    // 이메일 찾기
    @Override
    public ResponseEntity<ResponseDto> getUserEmail (GetFindEmailRequestDto dto) {
        String userName = dto.getUserName();
        String userPhoneNumber = dto.getUserPhonenumber();
        GetFindEmailResponseDto body = null;

        try {
            // 존재하지 않는 이름
            boolean name = userRepository.existsByName(userName);
            if(!name) return ResponseMessage.NOT_EXIST_USER_NAME;

            // 존재하지 않는 전화번호
            boolean phoneNumber = userRepository.existsByPhoneNumber(userPhoneNumber);
            if(!phoneNumber) return ResponseMessage.NOT_EXIST_PHONE_NUMBER;

            UserEntity findEntity = userRepository.findByNameAndPhoneNumber(userName, userPhoneNumber);

            body = new GetFindEmailResponseDto(findEntity.getEmail());
        
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
        

    // 회원가입
    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        
        String email = dto.getUserEmail();
        String phoneNumber = dto.getUserPhonenumber();
        String password = dto.getUserPassword();

        try {
            //* 존재하는 유저 이메일 반환 
            boolean existedUserEmail = userRepository.existsByEmail(email);
            if (existedUserEmail) return ResponseMessage.EXIST_USER_EMAIL;

            //* 존재하는 휴대폰 번호 반환 
            boolean existedUserPhoneNumber = userRepository.existsByPhoneNumber(phoneNumber);
            if (existedUserPhoneNumber) return ResponseMessage.EXIST_USER_PHONE_NUMBER;

            //* 매개변수 검증 실패
            if (dto.getClass() == null) return ResponseMessage.VAILDATION_FAILED;

            String encodedPassword = passwordEncoder.encode(password);
            dto.setUserPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch(Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseMessage.SUCCESS;

    }

    // 로그인
    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        
        SignInResponseDto body = null;

        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();

        if(adminEmail.equals(userEmail)) {
            if(!adminPassword.equals(userPassword)) return ResponseMessage.SIGN_IN_FAILED;

            String jwt = jwtTokenProvider.create(userEmail, "admin");
            body = new SignInResponseDto(jwt);
        } 

        try {
            //* 로그인 실패 반환 (이메일 X) 
            UserEntity userEntity = userRepository.findByEmail(userEmail);
            if (userEntity == null) return ResponseMessage.SIGN_IN_FAILED;

            //* 로그인 실패 반환 (패스워드 X) 
            String encordedPassword = userEntity.getPassword();
            boolean equaledPassword = passwordEncoder.matches(userPassword, encordedPassword);
            if (!equaledPassword) return ResponseMessage.SIGN_IN_FAILED;

            String jwt = jwtTokenProvider.create(userEmail, "normal");
            body = new SignInResponseDto(userEntity, jwt);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);

    }

}
