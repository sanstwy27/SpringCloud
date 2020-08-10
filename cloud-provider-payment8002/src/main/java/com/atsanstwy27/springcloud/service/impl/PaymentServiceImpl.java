package com.atsanstwy27.springcloud.service.impl;

import com.atsanstwy27.springcloud.dao.PaymentDao;
import com.atsanstwy27.springcloud.entities.Payment;
import com.atsanstwy27.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Sanstwy27
 * @create 8/9/2020
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
