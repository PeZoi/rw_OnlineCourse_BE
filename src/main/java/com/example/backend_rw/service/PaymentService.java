package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.payment.PaymentRequest;
import com.example.backend_rw.entity.dto.payment.PaymentResponse;

public interface PaymentService {
    PaymentResponse getPaymentInfo(PaymentRequest paymentRequest);
}
