package com.tangxy.springcloud.controller;

import com.tangxy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class PaymentController {
    @Autowired
    public PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String PaymentInfi_OK(@PathVariable("id") Integer id){
        String result=paymentService.PaymentInfi_OK(id);
        log.info("**********result:"+result);
        return result;
    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String PaymentInfi_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentService.PaymentInfi_TimeOut(id);
        log.info("**********result:" + result);
        return result;
    }
}
