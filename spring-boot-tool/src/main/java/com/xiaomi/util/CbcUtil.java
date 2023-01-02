package com.xiaomi.util;

import com.github.utils.sm4.SM4Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CbcUtil {
    public static String get32UUID() {
        // 1.开头两位，标识业务代码或机器代码（可变参数）
        int machineId = 11;
        // 2.中间四位整数，标识日期
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String dayTime = sdf.format(new Date());
        // 3.生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        // 4.可能为负数
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        // 5.算法处理: 0-代表前面补充0; 10-代表长度为10; d-代表参数为正数型
        String value = machineId + dayTime + String.format("%010d", hashCode);
        System.out.println(value);
        return value + value;
    }

    public static String SM4DecForCBC(String key, String text, String iv) {
        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = key;
        sm4.hexString = true;
        //sm4.iv = "31313131313131313131313131313131";
        sm4.iv = iv;
        String plainText = sm4.decryptData_CBC(text);
        return plainText;
    }

    public static String SM4EncForCBC(String key, String text, String iv) {
        SM4Utils sm4 = new SM4Utils();
        sm4.secretKey = key;
        sm4.hexString = true;
        //sm4.iv = "31313131313131313131313131313131";
        // 16位
        sm4.iv = iv;//"31313131313131313131313131313135";
        System.out.println("iv:  " + sm4.iv);
        String cipherText = sm4.encryptData_CBC(text);
        System.out.println("SM4的CBC加密 cipherText   :   " + cipherText);
        return cipherText;
    }

    public static String generateSM4Key() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
