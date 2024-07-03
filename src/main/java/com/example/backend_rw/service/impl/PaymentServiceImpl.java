package com.example.backend_rw.service.impl;

import com.example.backend_rw.entity.Courses;
import com.example.backend_rw.entity.User;
import com.example.backend_rw.entity.dto.payment.BankTransactionInfo;
import com.example.backend_rw.entity.dto.payment.PaymentRequest;
import com.example.backend_rw.entity.dto.payment.PaymentResponse;
import com.example.backend_rw.entity.dto.payment.TransactionRequest;
import com.example.backend_rw.exception.NotFoundException;
import com.example.backend_rw.repository.CoursesRepository;
import com.example.backend_rw.repository.OrderRepository;
import com.example.backend_rw.repository.UserRepository;
import com.example.backend_rw.service.OrderService;
import com.example.backend_rw.service.PaymentService;
import com.example.backend_rw.utils.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PaymentServiceImpl implements PaymentService {
    private String ACCOUNT_NAME = "PHẠM NGỌC VIỄN ĐÔNG"; // Không để được vào env do khi lấy ra nó không hỗ trợ utf-8
    private final CoursesRepository coursesRepository;
    private final UserRepository userRepository;
    private final OrderService orderService;
    private final Payment payment;
    @Value("${online.course.bank_number}")
    private String BANK_NUMBER;
    @Value("${online.course.bank_branch}")
    private String BANK_BRANCH;

    @Autowired
    public PaymentServiceImpl(CoursesRepository coursesRepository, UserRepository userRepository, OrderService orderService, Payment payment) {
        this.coursesRepository = coursesRepository;
        this.userRepository = userRepository;
        this.orderService = orderService;
        this.payment = payment;
    }

    @Override
    public PaymentResponse getPaymentInfo(PaymentRequest paymentRequest) {
        Courses course = coursesRepository.findById(paymentRequest.getCourseId()).orElseThrow(() -> new NotFoundException("Course ID không tồn tại"));
        User user = userRepository.findByEmail(paymentRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Email không tồn tại"));

        String content = String.format("TC%02d%02d", user.getId(), course.getId());
        int amount = (int) (course.getPrice() - (course.getPrice() * course.getDiscount()));

        String qrCode = String.format("https://img.vietqr.io/image/970423-%s-qr_only.png?amount=%d&addInfo=%s&accountName=%s", BANK_NUMBER, amount, content, ACCOUNT_NAME);

        return PaymentResponse.builder().qrCode(qrCode).bankNumber(BANK_NUMBER).content(content).accountName(ACCOUNT_NAME).bankBranch(BANK_BRANCH).build();
    }

    @Override
    public boolean checkTransaction(TransactionRequest transactionRequest) {
        Courses course = coursesRepository.findById(transactionRequest.getCourseId()).orElseThrow(() -> new NotFoundException("Course ID không tồn tại"));
        User user = userRepository.findByEmail(transactionRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Email không tồn tại"));

        // Kiểm tra xem có giao dịch này trong lịch sử giao dịch của bank không
        BankTransactionInfo[] bankTransactionInfos = payment.getTransactions();
        for (BankTransactionInfo bankTransactionInfo : bankTransactionInfos) {

            if (Integer.parseInt(bankTransactionInfo.getAmount()) >= transactionRequest.getTotalPrice() && bankTransactionInfo.getDescription().contains(transactionRequest.getDescription())) {
//                orderService.createOrder(user, course, transactionRequest.getTotalPrice());
                return true;
            }
        }
        return false;
    }
}
