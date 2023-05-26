package com.travelproject.travelproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.travelproject.travelproject.entity.PaymentEntity;


public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {

    public PaymentEntity findByTransactionId(String transactionId);

    @Query (
        value = 
        "SELECT * " +
        "FROM Payment " +
        "WHERE payment_user_email = ? " +
        "ORDER BY payment_datetime DESC",
        nativeQuery = true
    )
    public List<PaymentEntity> getPaymentList(String paymentUserEmail);


    @Query (
        value = 
        "SELECT " +
        "SUM(payment_amount) AS yearSale " +
        "FROM Payment " +
        "WHERE payment_datetime LIKE ? " +
        "GROUP BY payment_amount",
        nativeQuery = true
    )
    public Integer paymentYearTotalSale(String year);

    @Query (
        value = 
        "SELECT " +
        "SUM(payment_amount) AS monthSale " +
        "FROM Payment " +
        "WHERE payment_datetime LIKE ? " +
        "GROUP BY payment_amount",
        nativeQuery = true
    )
    public Integer paymentMonthTotalSale(String month);

    @Query (
        value = 
        "SELECT " +
        "SUM(payment_amount) AS daySale " +
        "FROM Payment " +
        "WHERE payment_datetime LIKE ? " +
        "GROUP BY payment_amount",
        nativeQuery = true
    )
    public Integer paymentDayTotalSale(String day);

    @Query (
        value = 
        "SELECT * " +
        "FROM Payment " +
        "ORDER BY payment_datetime DESC",
        nativeQuery = true
    )
    public List<PaymentEntity> getPaymentList();
}
