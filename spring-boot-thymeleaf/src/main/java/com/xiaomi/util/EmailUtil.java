package com.xiaomi.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class EmailUtil {

    /**
     * 自定义发件人邮箱
     *
     * @param from    发件人邮箱
     * @param to      收件人邮箱
     * @param subject 邮件标题
     * @param text    邮件内容
     * @throws IOException
     * @throws MessagingException
     */
    public static void sendMail(String from, String[] to, String host, String password, String subject, String text, List<File> fileList) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setUsername(from);
        mailSender.setPassword(password);

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", false);// 参数设为true，需服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", 5000);
        mailSender.setJavaMailProperties(prop);
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            mailSender.setHost(host);
            mailSender.setPassword(password);
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
            messageHelper.setTo(to);
            messageHelper.setFrom(from);
            if (fileList.size() != 0) {
                for (File file : fileList) {
                    messageHelper.addAttachment(file.getName(), file);
                }
            }
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 默认发件人邮箱
     *
     * @param to       收件人邮箱
     * @param subject  标题
     * @param text     内容
     * @param fileList 文件
     */
    public static void sendNoFromMail(String[] to, String subject, String text, List<File> fileList, JavaMailSenderImpl mailSender) {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", false);// 参数设为true，需服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", 5000);
        mailSender.setJavaMailProperties(prop);
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            messageHelper.setSubject(subject);
            messageHelper.setText(text, true);
            messageHelper.setTo(to);
            String username = mailSender.getUsername();
            if (StringUtils.isNotBlank(username)) {
                messageHelper.setFrom(username);
            }
            if (fileList.size() != 0) {
                for (File file : fileList) {
                    messageHelper.addAttachment(file.getName(), file);
                }
            }
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}