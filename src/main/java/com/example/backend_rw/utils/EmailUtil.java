package com.example.backend_rw.utils;

import com.example.backend_rw.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class EmailUtil {
    @Value("${online.course.email}")
    private String email;
    private final JavaMailSender javaMailSender;

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
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Send email failed!");
        }

        content = content.replace("[[name]]", user.getFullName());

        content = content.replace("[[URL]]", url);
        try {
            helper.setText(content, true);
        } catch (MessagingException e) {
//            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Send email failed!");
        }
        javaMailSender.send(message);
    }
}
