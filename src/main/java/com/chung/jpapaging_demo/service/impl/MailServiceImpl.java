package com.chung.jpapaging_demo.service.impl;

import com.chung.jpapaging_demo.service.MailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;


@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String email;

    @Override
    public void sendMail() {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message
                    , MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.setFrom(email);
            helper.setTo("cuibap20011110@gmail.com");
            helper.setText("Hello my name is Chung. This is email from chung");
            helper.setSubject("Mail test");
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
