package com.xiaomi.util;
 
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
public class SysTemplateUtil {
 
    public static String getEmailTemplateContent(String templateContent, Map<String, String> valueMap) {
        // 正则匹配 {xx}
        String reg = "\\{(.*?)\\}";
        Pattern regex = Pattern.compile(reg);
        // 占位符可能匹配的次数
        int count = templateContent.split("\\{").length - 1;
        System.out.println("占位符可能匹配的次数:" + count);
        for (int j = 0; j < count; j++) {
            Matcher matcher = regex.matcher(templateContent);
            boolean flag = matcher.find();
            if (!flag) {
                // 没有匹配到，结束
                break;
            }
            // 取得匹配到的 ${xxx}
            String key = matcher.group(0);
            if (valueMap.containsKey(key)) {
                templateContent = templateContent.replace(key, valueMap.get(key));
            }
            System.out.println("第" + j + "次值：" + templateContent);
        }
        return templateContent;
    }
}