package com.tangxy.springcloud.controller;

import com.tangxy.springcloud.entities.CommonResult;
import com.tangxy.springcloud.entities.Payment;
import com.tangxy.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@Slf4j
public class PaymentController {
    @Autowired
    public PaymentService paymentService;

    @Value("${server.port}")
    public String serverPort;


    @Resource
    public DiscoveryClient discoveryClient;

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

    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element:services
        ) {
            log.info("****element:"+element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: instances
        ) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());

        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
    @GetMapping("payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return serverPort;
    }
}
