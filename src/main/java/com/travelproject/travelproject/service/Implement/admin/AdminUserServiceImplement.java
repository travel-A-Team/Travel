package com.travelproject.travelproject.service.Implement.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.common.util.UserTokenAdminRoleValidation;
import com.travelproject.travelproject.dto.response.admin.user.GetUserListResponseDto;
import com.travelproject.travelproject.entity.UserEntity;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.UserRepository;
import com.travelproject.travelproject.service.admin.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminUserServiceImplement implements UserService{

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetUserListResponseDto> getUserList(UserToken userToken) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        GetUserListResponseDto body = null;

        try {

            List<UserEntity> userEntities = userRepository.getUserList();
            body = new GetUserListResponseDto(userEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
}
