package com.travelproject.travelproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.travelproject.travelproject.dto.request.questionBoard.PostProductLikeyRequestDto;
import com.travelproject.travelproject.entity.primaryKey.LikeyPk;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "Likey")
@Table(name = "Likey")
@IdClass(LikeyPk.class)
public class LikeyEntity {
    
    @Id
    private int likeyProduct;
    @Id 
    private String likeyUserEmail;
    
    public LikeyEntity(String productLikeyEmail, PostProductLikeyRequestDto dto){
        this.likeyProduct = dto.getProductBoardNumber();
        this.likeyUserEmail = productLikeyEmail;
    }
}
