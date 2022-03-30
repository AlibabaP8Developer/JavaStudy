package com.xiaomi.controller;

import com.xiaomi.config.SmsListener;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    SmsListener smsListener;

    @GetMapping("/sendCode")
    public String sendCode(@RequestParam("phone") String phone) {
        String code = RandomStringUtils.randomNumeric(6);
        smsListener.sendSms(phone, code);
        return "ok";
    }
}
