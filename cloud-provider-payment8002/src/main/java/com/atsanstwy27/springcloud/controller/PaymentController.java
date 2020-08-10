package com.atsanstwy27.springcloud.controller;

import com.atsanstwy27.springcloud.entities.CommonResult;
import com.atsanstwy27.springcloud.entities.Payment;
import com.atsanstwy27.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Sanstwy27
 * @create 8/10/2020
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***** insert result:" + result);

        if(result > 0) {
            return new CommonResult(200, "insert success! serverPort:" + serverPort, result);
        } else {
            return new CommonResult(444, "insert failed!", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("***** query result:" + payment);

        if(payment != null) {
            return new CommonResult(200, "query success! serverPort:" + serverPort, payment);
        } else {
            return new CommonResult(444, "query failed!", null);
        }
    }
}
