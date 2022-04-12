package com.xiaomi.controller;

import com.alibaba.util.R;
import com.xiaomi.util.CompressUtil;
import com.xiaomi.util.PdfToWordUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/tool")
public class FileController {

    @Value("${config.osxDir}")
    private String osXDir;
    @Value("${config.linuxDir}")
    private String linuxDir;
    @Value("${config.windowsDir}")
    private String windowsDir;

    @PostMapping("/pdfToWord")
    public void pdfToWord(@RequestParam("pdf") MultipartFile pdf, HttpServletResponse response) {
        String path = "";
        String property = System.getProperty("os.name");
        if (property.equals("Mac OS X")) {
            path = osXDir;
        } else if (property.toLowerCase().indexOf("win") > 0) {
            path = windowsDir;
        } else {
            path = linuxDir;
        }
        String originalFilename = pdf.getOriginalFilename();
        String filename = pdf.getName();
        String file = path + originalFilename;
        System.out.println(file);
        Map<String, Object> result = new PdfToWordUtil().pdftoword(file);
        System.out.println("result: "+result);
        String desPath = (String) result.get("desPath");
        try {
            String zipFile = CompressUtil.zipFile(new File(desPath), "zip");
            response.setContentType("APPLICATION/OCTET-STREAM");
            String fileName = "ZipPdf-" + originalFilename.substring(0, originalFilename.lastIndexOf(".")) + ".zip";
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            OutputStream out = response.getOutputStream();
            File ftp = ResourceUtils.getFile(zipFile);
            InputStream in = new FileInputStream(ftp);

            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }

            File zip = new File(zipFile);
            if (zip.exists()) {
                if (zip.isFile()) {
                    zip.delete();
                }
            }
            File desPath1 = new File(desPath);
            if (desPath1.exists()) {
                if (desPath1.isFile()) {
                    desPath1.delete();
                }
            }
            System.out.println("zipFile: "+zipFile);
            System.out.println("filename: "+filename);
            System.out.println("fileName: "+fileName);
            System.out.println("originalFilename: "+originalFilename);
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
