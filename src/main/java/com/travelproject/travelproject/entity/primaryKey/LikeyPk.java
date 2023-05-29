package com.travelproject.travelproject.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;

public class LikeyPk implements Serializable {
    
    @Column(name = "product_number")
    private int productNumber;
    @Column(name = "user_email")
    private String userEmail;
    
}
