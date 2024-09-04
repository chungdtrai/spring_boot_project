package com.chung.jpapaging_demo.controller;

import com.chung.jpapaging_demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    @GetMapping("/send_test")
    public String sendTestMail() {
        mailService.sendMail();
        return "success";
    }
}
