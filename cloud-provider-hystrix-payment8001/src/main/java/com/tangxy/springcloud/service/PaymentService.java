package com.tangxy.springcloud.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String PaymentInfi_OK(Integer id){
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id:"+id+"\t"+"haha!!!";
    }

    public String PaymentInfi_TimeOut(Integer id){
        int timeNumber=3;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "线程池："+Thread.currentThread().getName()+"   paymentInfo_OK,id:"+id+"\t"+"haha!!!"+"耗时"+timeNumber+"秒钟";
    }
}

