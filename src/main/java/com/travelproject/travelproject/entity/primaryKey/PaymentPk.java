package com.travelproject.travelproject.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;

public class PaymentPk implements Serializable {
    @Column(name = "partner_order_id")
    private String partnerOrderId;
    @Column(name = "partner_user_id")
    private String partnerUserId;
}
