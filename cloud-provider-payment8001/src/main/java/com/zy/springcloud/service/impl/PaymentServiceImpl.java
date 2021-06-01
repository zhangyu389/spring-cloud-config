package com.zy.springcloud.service.impl;

import com.zy.springcloud.dao.PaymentDao;
import com.zy.springcloud.entities.Payment;
import com.zy.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired(required = false)
    private PaymentDao paymentDao;

    @Override
    public int save(Payment payment) {
        int save = paymentDao.save(payment);
        return save;
    }

    @Override
    public List all() {
        return null;
    }

    @Override
    public Payment getPaymentById(long id) {
        Payment paymentById = paymentDao.getPaymentById(id);
        return paymentById;
    }
}
