package com.xiaomi.controller;

import com.xiaomi.util.EmailUtil;
import com.xiaomi.util.FileUtil;
import com.xiaomi.util.QRcodeZxingUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/tool")
public class ToolController {

    @Autowired
    private JavaMailSenderImpl mailSender;

    /**
     * 文件操作
     * @param info 输入要追加的指定内容
     * @param replaceTo 替换的内容
     * @return
     */
    @RequestMapping("/file")
    public String file(String info, String replaceTo) {
        if (StringUtils.isBlank(replaceTo)) {
            FileUtil.fileNameAddTo(new File("/Users/lizhenghang/Desktop"), info);
        } else {
            FileUtil.fileNameManage(new File("/Users/lizhenghang/Desktop"), info, replaceTo);
        }
        return "ok";
    }

    /**
     * 生成二维码
     * @return
     */
    @GetMapping("/qrcode")
    public String qr(String info, int width, int height) {
        QRcodeZxingUtil.generateQRcodePic(info, width, height, "jpg", new File("/Users/lizhenghang/Desktop"));
        return "ok";
    }

    /**
     * 发邮件
     */
    @RequestMapping("/sendMail")
    public String sendMail(@RequestBody Map<String, String> info) {
        String host = info.get("host");
        String password = info.get("password");
        String title = info.get("title");
        String content = info.get("content");
        String from = info.get("from");
        String[] to = info.get("to").split(",");

        if (StringUtils.isNotBlank(from)) {
            EmailUtil.sendMail(from, to, host, password, title, content, new ArrayList<>());
        } else {
            EmailUtil.sendNoFromMail(to, title, content, new ArrayList<File>(), mailSender);
        }
        return "ok";
    }

}
