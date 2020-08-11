package com.atsanstwy27.springcloud.controller;

import com.atsanstwy27.springcloud.entities.CommonResult;
import com.atsanstwy27.springcloud.entities.Payment;
import com.atsanstwy27.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Sanstwy27
 * @create 8/11/2020
 */
@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // openfeign-ribbon, default waiting time is 1 second
        return paymentFeignService.paymentFeignTimeout();
    }
}
