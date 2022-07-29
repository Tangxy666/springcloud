package com.tangxy.springcloud.controller;

import com.tangxy.springcloud.entities.CommonResult;
import com.tangxy.springcloud.entities.Payment;
import com.tangxy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Slf4j
public class PaymentController {
    @Autowired
    public PaymentService paymentService;

    @Value("${server.port}")
    public String serverPort;


    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result=paymentService.create(payment);
        log.info("插入结果："+result);
        if (result>0){
            return new CommonResult(200,"插入数据库成功,serverport:"+serverPort,result);
        }
        else{
            return new CommonResult(444,"插入数据库失败",null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentId(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment!=null)
            return new CommonResult(200,"查询成功,serverport:"+serverPort,payment);
        else
            return new CommonResult(444,"没有对应记录，查询id："+id,null);
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

}
