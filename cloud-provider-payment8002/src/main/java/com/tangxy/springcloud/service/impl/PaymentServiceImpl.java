package com.tangxy.springcloud.service.impl;

import com.tangxy.springcloud.dao.PaymentDao;
import com.tangxy.springcloud.entities.Payment;
import com.tangxy.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
