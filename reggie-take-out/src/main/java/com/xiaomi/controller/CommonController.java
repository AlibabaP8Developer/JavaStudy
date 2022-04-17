package com.xiaomi.controller;

import com.xiaomi.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 上传和下载
 */
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {

    @Value("${config.osxDir}")
    private String osXDir;
    @Value("${config.linuxDir}")
    private String linuxDir;
    @Value("${config.windowsDir}")
    private String windowsDir;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file) {
        log.info("file:{}", file);
        String path = "";
        String property = System.getProperty("os.name");
        if (property.equals("Mac OS X")) {
            path = osXDir;
        } else if (property.toLowerCase().indexOf("win") > 0) {
            path = windowsDir;
        } else {
            path = linuxDir;
        }

        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonth().getValue();
        int dayOfMonth = now.getDayOfMonth();
        String datePath = year + "/" + month + "/" + dayOfMonth + "/";
        path += datePath;

        // 原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 生成新文件名，防止文件名称重复
        String fileName = UUID.randomUUID().toString() + suffix;

        // 创建一个目录
        File dir = new File(path);
        // 判断当前目录是否存在
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(path + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.success(datePath + fileName);
    }

    /**
     * 文件下载
     *
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        String path = "";
        String property = System.getProperty("os.name");
        if (property.equals("Mac OS X")) {
            path = osXDir;
        } else if (property.toLowerCase().indexOf("win") > 0) {
            path = windowsDir;
        } else {
            path = linuxDir;
        }

        try {
            // 通过输入流读取文件内容
            FileInputStream fis = new FileInputStream(new File(path + name));
            // 通过输出流将文件写回浏览器，在浏览器展示图片了
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];

            while ((len = fis.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
                outputStream.flush();
            }

            outputStream.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
