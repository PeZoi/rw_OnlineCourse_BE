package com.example.backend_rw.utils;

import com.example.backend_rw.entity.Order;
import com.example.backend_rw.entity.User;
import com.example.backend_rw.exception.CustomException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class EmailUtil {
    private final JavaMailSender javaMailSender;
    @Value("${online.course.email}")
    private String email;

    public EmailUtil(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(String url, String subject, String content, User user) {


//        Lấy ra email chuẩn bị gửi
        String toAddress = user.getEmail();

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(email, "Tech Courses");
            helper.setTo(toAddress);
            helper.setSubject(subject);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new CustomException("Lỗi không gửi được email!", HttpStatus.BAD_REQUEST);
        }

        content = content.replace("[[name]]", user.getFullName());

        content = content.replace("[[URL]]", url);
        try {
            helper.setText(content, true);
        } catch (MessagingException e) {
            throw new CustomException("Lỗi không gửi được email!", HttpStatus.BAD_REQUEST);
        }
        javaMailSender.send(message);
    }

    @Async
    public void sendEmailForOrder(String subject, String content, Order order) {

        String toAddress = order.getUser().getEmail();

        subject = subject.replace("[[orderId]]", order.getId().toString());

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("tech.courses.895@gmail.com", "Tech Courses");
            helper.setTo(toAddress);
            helper.setSubject(subject);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new CustomException("Lỗi không gửi được email!", HttpStatus.BAD_REQUEST);
        }

        content = content.replace("[[name]]", order.getUser().getFullName());
        content = content.replace("[[orderId]]", order.getId().toString());
        content = content.replace("[[orderTime]]", order.getCreatedTime().toString());
        content = content.replace("[[courseName]]", order.getCourses().getTitle());
        content = content.replace("[[total]]", Integer.toString(order.getTotalPrice()));

        try {
            helper.setText(content, true);
        } catch (MessagingException e) {
            throw new CustomException("Lỗi không gửi được email!", HttpStatus.BAD_REQUEST);
        }
        javaMailSender.send(message);
    }
}
