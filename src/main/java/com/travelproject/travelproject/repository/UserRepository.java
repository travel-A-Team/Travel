package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.travelproject.travelproject.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    
    @Query (
        value = 
        "SELECT * " +
        "FROM User " +
        "ORDER BY register_datetime DESC",
         nativeQuery = true
    )
    public List<UserEntity> getUserList();

     //! 회원가입 시 사용
     public boolean existsByName(String name);
     public boolean existsByEmail(String email);
     public boolean existsByPhoneNumber(String phoneNumber);
 
     //! 이메일 찾기, 유저 프로필 조회 시 사용
     public UserEntity findByEmail(String email);
     public UserEntity findByNameAndPhoneNumber(String name, String phoneNumber);
 
     //! 비밀번호 찾기 시 사용
     public UserEntity findByEmailAndPhoneNumber(String email, String phoneNumner);
 
     //! 비밀번호 변경 시 사용
     public UserEntity findByPassword(String oldPassword);
}
