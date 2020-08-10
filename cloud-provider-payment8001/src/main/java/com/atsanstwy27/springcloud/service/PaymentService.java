package com.atsanstwy27.springcloud.service;

import com.atsanstwy27.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * @author Sanstwy27
 * @create 8/9/2020
 */
@Service
public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);
}
