package com.example.backend_rw.service;

import com.example.backend_rw.entity.dto.payment.PaymentRequest;
import com.example.backend_rw.entity.dto.payment.PaymentResponse;
import com.example.backend_rw.entity.dto.payment.TransactionRequest;

public interface PaymentService {
    PaymentResponse getPaymentInfo(PaymentRequest paymentRequest);
    boolean checkTransaction(TransactionRequest transactionRequest);
}
