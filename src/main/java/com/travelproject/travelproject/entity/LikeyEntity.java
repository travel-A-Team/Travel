package com.travelproject.travelproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.travelproject.travelproject.entity.primaryKey.LikeyPk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Likey")
@Table(name = "Likey")
@IdClass(LikeyPk.class)
public class LikeyEntity {
    
    @Id
    private int likeyProduct;
    @Id 
    private String likeyUserEmail;
    
}
