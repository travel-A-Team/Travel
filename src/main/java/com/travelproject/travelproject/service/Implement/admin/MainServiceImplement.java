package com.travelproject.travelproject.service.Implement.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.travelproject.travelproject.common.constant.ResponseMessage;
import com.travelproject.travelproject.common.util.UserTokenAdminRoleValidation;
import com.travelproject.travelproject.dto.request.admin.main.PostSignInRequestDto;
import com.travelproject.travelproject.dto.response.admin.main.GetPaymentListResponseDto;
import com.travelproject.travelproject.dto.response.admin.main.GetPaymentTotalSaleResponseDto;
import com.travelproject.travelproject.dto.response.admin.main.GetTouristProductListResponseDto;
import com.travelproject.travelproject.dto.response.admin.main.GetUserListResponseDto;
import com.travelproject.travelproject.dto.response.admin.main.PostSignInResponseDto;
import com.travelproject.travelproject.entity.PaymentEntity;
import com.travelproject.travelproject.entity.TouristProductEntity;
import com.travelproject.travelproject.entity.UserEntity;
import com.travelproject.travelproject.provider.JwtTokenProvider;
import com.travelproject.travelproject.provider.UserToken;
import com.travelproject.travelproject.repository.PaymentRepository;
import com.travelproject.travelproject.repository.TouristProductRepository;
import com.travelproject.travelproject.repository.UserRepository;
import com.travelproject.travelproject.service.admin.MainService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class MainServiceImplement implements MainService {
    
    @Value("${admin.email}")
    String email;
    @Value("${admin.password}")
    String password;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;
    private final TouristProductRepository touristProductRepository;
    


    @Override
    public ResponseEntity<? super PostSignInResponseDto> signIn(PostSignInRequestDto dto) {

        PostSignInResponseDto body = null;
        String adminEamil = dto.getAdminEmail();
        String adminPassword = dto.getAdminPassword();

            if (!adminEamil.equals(email)) return ResponseMessage.SIGN_IN_FAILED;
            System.out.println("email"+email);

            if (!adminPassword.equals(password)) return ResponseMessage.SIGN_IN_FAILED;
            
            String role = "admin";
            String jwt = jwtTokenProvider.create(adminEamil, role);
            System.out.println("jwt 값: " + jwt);
            body = new PostSignInResponseDto(jwt);

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


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


    @Override
    public ResponseEntity<? super GetPaymentTotalSaleResponseDto> getPaymentTotalSale(UserToken userToken) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        GetPaymentTotalSaleResponseDto body = null;

        Date now = new Date();
        SimpleDateFormat yearSimpleDateFormat = new SimpleDateFormat("yyyy%");
        String year = yearSimpleDateFormat.format(now);
        
        SimpleDateFormat monthSimpleDateFormat = new SimpleDateFormat("yyyy-MM%");
        String month = monthSimpleDateFormat.format(now);

        SimpleDateFormat daySimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String day = daySimpleDateFormat.format(now);
        

        try {
            Integer yearSale  = paymentRepository.paymentYearTotalSale(year);
            
            
            Integer monthSale  = paymentRepository.paymentMonthTotalSale(month);
           
            
            Integer daySale  = paymentRepository.paymentDayTotalSale(day);
            
            

            body = new GetPaymentTotalSaleResponseDto(daySale, monthSale, yearSale);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


    @Override
    public ResponseEntity<? super GetPaymentListResponseDto> getPaymentList(UserToken userToken) {

        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        GetPaymentListResponseDto body = null;

        try {
            List<PaymentEntity> paymentEntities = paymentRepository.getPaymentList();
            body = new GetPaymentListResponseDto(paymentEntities);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }


    @Override
    public ResponseEntity<? super GetTouristProductListResponseDto> getTouristProductList(UserToken userToken) {
        
        boolean adminRole = UserTokenAdminRoleValidation.adminRoleValidation(userToken);
        if (!adminRole) return ResponseMessage.NO_PERMISSIONS;

        GetTouristProductListResponseDto body = null;

        try {
            List<TouristProductEntity> touristProductEntities = touristProductRepository.getTouristProductListInAdminMainPage();
            body = new GetTouristProductListResponseDto(touristProductEntities);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseMessage.DATABASE_ERROR;
        }
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
}
