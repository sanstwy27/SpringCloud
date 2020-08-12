package com.atsanstwy27.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

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

    // Circuit Breaker
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            // request times
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            // time interval
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            // threshold of error rate
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id must be non-negative");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "success, serialNumber: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id must be non-negative /(ㄒoㄒ)/~~   id: " +id;
    }
}
