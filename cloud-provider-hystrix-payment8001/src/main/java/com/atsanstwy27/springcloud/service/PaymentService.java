package com.atsanstwy27.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Sanstwy27
 * @create 8/12/2020
 */
@Service
public class PaymentService {
    public String paymentInfo_OK(Integer id) {
        return "threadpool: [" + Thread.currentThread().getName() + "] paymentInfo_OK, id: " + id;
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        //int age = 10 / 0;
        int timeNumber = 3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return "threadpool: [" + Thread.currentThread().getName() + "] paymentInfo_OK, id: " + id + ",\t sleep " + timeNumber + " seconds";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "threadpool: [" + Thread.currentThread().getName() + "] paymentInfo_TimeOutHandler8001, id: " + id + " service error or busy";
    }
}
