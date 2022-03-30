package com.xiaomi.config;

import com.aliyuncs.exceptions.ClientException;
import com.xiaomi.util.SmsUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sms")
@Data
@Component
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    private String templateCode;
    private String signName;
    private String code;

    /**
     * 发送短信 注册
     *
     * @param phone
     * @param code
     */
    public void sendSms(String phone, String code) {
        try {
            smsUtil.sendSms(phone, templateCode, signName, "{\"code\":\"" + code + "\"}");
            System.out.println("手机号：" + phone);
            System.out.println("验证码：" + code);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

}
