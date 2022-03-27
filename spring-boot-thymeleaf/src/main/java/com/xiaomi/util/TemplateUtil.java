package com.xiaomi.util;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TemplateUtil {

    public static void template(String templateName,Context context, File file) throws IOException {
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
        FileWriter writer = new FileWriter(file);
        //创建静态文件,"text"是模板html名字
        templateEngine.process(templateName, context, writer);
    }
}
