package com.xiaomi.controller;

import com.xiaomi.util.CompressUtil;
import com.xiaomi.util.PdfToImgUtil;
import com.xiaomi.util.PdfToWordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tool")
public class FileController {

    @Value("${config.osxDir}")
    private String osXDir;
    @Value("${config.linuxDir}")
    private String linuxDir;
    @Value("${config.windowsDir}")
    private String windowsDir;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 若下载文件没有.zip后缀手动补全
     *
     * @param pdf
     * @param response
     */
    @PostMapping("/pdfToWord")
    public void pdfToWord(@RequestParam("pdf") MultipartFile pdf, HttpServletResponse response) {
        String path1 = stringRedisTemplate.opsForValue().get("path");
        String path = "";
        String property = System.getProperty("os.name");
        if (property.equals("Mac OS X")) {
            path = osXDir;
        } else if (property.toLowerCase().indexOf("win") > 0) {
            path = windowsDir;
        } else {
            path = linuxDir + path1;
        }
        String docPath = "doc/";
        String splitPath = "split/";
        String doc = path + docPath;
        String split = path + splitPath;

        String originalFilename = pdf.getOriginalFilename();
        String filename = pdf.getName();
        String file = path + originalFilename;
        System.out.println(file);
        Map<String, Object> result = new PdfToWordUtil().pdftoword(file, doc, split);
        System.out.println("result: " + result);
        String desPath = (String) result.get("desPath");
        try {
            String zipFile = CompressUtil.zipFile(new File(desPath), "zip");
            response.setContentType("APPLICATION/OCTET-STREAM");
            String name = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            String fileName = "ZipPdf-" + name + ".zip";
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
            System.out.println("zipFile: " + zipFile);
            System.out.println("filename: " + filename);
            System.out.println("fileName: " + fileName);
            System.out.println("originalFilename: " + originalFilename);
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/pdfToImage")
    public void pdfToImage(@RequestParam("pdf") MultipartFile pdf, HttpServletResponse response) {
        String path1 = stringRedisTemplate.opsForValue().get("path");
        String path = "";
        String property = System.getProperty("os.name");
        if (property.equals("Mac OS X")) {
            path = osXDir;
        } else if (property.toLowerCase().indexOf("win") > 0) {
            path = windowsDir;
        } else {
            path = linuxDir + path1;
        }
        String originalFilename = pdf.getOriginalFilename();
        String filename = pdf.getName();
        List<String> pdfToimg = PdfToImgUtil.pdfToImg(path + originalFilename, path + "pdf-to-img");
        System.out.println(pdfToimg);
        String s = pdfToimg.get(0);
        String desPath = s.substring(0, s.lastIndexOf('/'));
        try {
            String zipFile = CompressUtil.zipFile(new File(desPath), "zip");
            response.setContentType("APPLICATION/OCTET-STREAM");
            String name1 = originalFilename.substring(0, originalFilename.lastIndexOf("."));
            String fileName = "zip-image-" + name1 + ".zip";
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
            System.out.println("zipFile: " + zipFile);
            System.out.println("filename: " + filename);
            System.out.println("fileName: " + fileName);
            System.out.println("originalFilename: " + originalFilename);
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
