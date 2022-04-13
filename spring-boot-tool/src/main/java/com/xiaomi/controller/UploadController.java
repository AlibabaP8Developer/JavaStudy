package com.xiaomi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@Controller
@RequestMapping("")
public class UploadController {

    @Value("${config.osxDir}")
    private String osXDir;
    @Value("${config.linuxDir}")
    private String linuxDir;
    @Value("${config.windowsDir}")
    private String windowsDir;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/upload")
    public String upload(@RequestParam("upload") MultipartFile upload) throws Exception {
        // 上传的位置
        String path = "";
        String property = System.getProperty("os.name");
        if (property.equals("Mac OS X")) {
            path = osXDir;
        } else if (property.toLowerCase().indexOf("win") > 0) {
            path = windowsDir;
        } else {
            path = linuxDir;
        }

        // 按日期生成相对路径
        String dataPath = "file/" + Calendar.YEAR + "/" + Calendar.MARCH + "/" + Calendar.DAY_OF_MONTH + "/";
        String p = path + dataPath;
        File file = new File(p);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 获取上传文件项
        String oriName = upload.getOriginalFilename();
        //String extName = oriName.substring(oriName.lastIndexOf("."));
        // 修改文件名
        //String fileName = UUID.randomUUID().toString().replace("-", "");
        // 新文件名
        //String newFilename = fileName + extName;
        // 完成上传
        upload.transferTo(new File(p, oriName));

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("path", dataPath + oriName);
        map.put("fileName", oriName);
        stringRedisTemplate.opsForValue().set("filePath", dataPath + oriName);
        stringRedisTemplate.opsForValue().set("path", dataPath);
        return "index";
    }
}
