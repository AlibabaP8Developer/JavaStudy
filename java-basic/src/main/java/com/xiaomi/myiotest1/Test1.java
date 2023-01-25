package com.xiaomi.myiotest1;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {

    public static void main(String[] args) {
        // 1.定义变量记录网址
        String familyNameNet = "https://hanyu.baidu.com/s?wd=%E7%99%BE%E5%AE%B6%E5%A7%93&device=pc&from=home";
        var boyNameNet = "http://www.haoming8.cn/baobao/11837.html";
        var grilNameNet = "http://www.haoming8.cn/baobao/11843.html";
        // 2.爬取数据
        try {
            String familyName = webCrawler(familyNameNet);
            String boyName = webCrawler(boyNameNet);
            String grilName = webCrawler(grilNameNet);
            // 3.通过正则表达式，把其中符合要求的数据获取出来
            String regex = "(.{4})(. |。)";
            ArrayList<String> familyNameTempList = getData(familyName, regex, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 作用：根据正则表达式获取字符串中的数据
     *
     * @param str 完整的字符串
     * @param regex 正则表达式
     * @return 想要的数据
     */
    private static ArrayList<String> getData(String str, String regex, int index) {
        // 1.创建集合存储数据
        ArrayList<String> list = new ArrayList<>();
        // 2.按照正则表达式的规则，去获取数据
        Pattern pattern = Pattern.compile(regex);
        // 3.按照pattern的规则，到str当中获取数据
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            String group = matcher.group(index);
            System.out.println(group);
        }
        return list;
    }

    /**
     * 作用：
     * 从网络上爬取数据，数据拼接成字符串返回
     * 形参：网址
     * 返回值：爬取到的所有数据
     *
     * @return
     */
    public static String webCrawler(String net) throws Exception {
        // 1.StringJoiner拼接爬取到的数据
        StringBuilder sb = new StringBuilder();
        // 2.URL对象
        URL url = new URL(net);
        // 3.连接网址
        URLConnection conn = url.openConnection();
        // 4.读取数据
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        int ch = 0;
        while ((ch = isr.read()) != -1) {
            sb.append((char) ch);
        }
        //释放资源
        isr.close();
        // 返回数据
        return sb.toString();
    }
}
