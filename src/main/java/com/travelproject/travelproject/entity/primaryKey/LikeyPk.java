package com.travelproject.travelproject.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;

public class LikeyPk implements Serializable {
    
    @Column(name = "likey_product")
    private int likeyProduct;
    @Column(name = "likey_user_email")
    private String likeyUserEmail;
    
}
