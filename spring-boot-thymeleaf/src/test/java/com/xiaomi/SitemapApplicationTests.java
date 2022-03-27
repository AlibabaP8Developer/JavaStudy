package com.xiaomi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.io.IOException;

@SpringBootTest
class SitemapApplicationTests {

    @Value("${filepath}")
    String filepath;

    @Test
    void contextLoads() {
        //创建模版加载器
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        //模板文件的所在目录
        resolver.setPrefix("templates/");
        //模板文件后缀
        resolver.setSuffix(".html");
        //创建模板引擎
        TemplateEngine templateEngine = new TemplateEngine();
        //将加载器放入模板引擎
        templateEngine.setTemplateResolver(resolver);
        //创建字符输出流并且自定义输出文件的位置和文件名
        FileWriter writer = null;
        try {
            writer = new FileWriter(filepath+"/meetingList.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建Context对象(存放Model)
        Context context = new Context();
        //放入数据
        context.setVariable("meetingName", "刚果（金）-尼日利亚电网互联项目");
        //创建静态文件,"text"是模板html名字
        templateEngine.process("detail", context, writer);
    }

}
