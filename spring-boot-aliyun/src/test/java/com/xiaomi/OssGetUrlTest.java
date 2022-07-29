package com.xiaomi;

import com.aliyun.oss.*;

import java.net.URL;

import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OssGetUrlTest {

    public static void main(String[] args) throws Throwable {
        // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = "LTAI5tH1DEGfmGHeMChTf4Mt";
        String accessKeySecret = "tqgDaBSkVHwSLf2jZagUA4v49IGi1d";
        // 从STS服务获取的安全令牌（SecurityToken）。
        String securityToken = "CAISiwJ1q6Ft5B2yfSjIr5bnIMzN2LpF4reGTmHehmI9b+dPnInM0zz2IHpLdHNqCOsYtvQ3mm9Q6P4flrh+W4NIX0rNaY5t9ZlN9wqkbtIEHGtMGeRW5qe+EE2/VjTZvqaLEcibIfrZfvCyESOm8gZ43br9cxi7QlWhKufnoJV7b9MRLGLaBHg8c7UwHAZ5r9IAPnb8LOukNgWQ4lDdF011oAFx+wgdgOadupTMt0CC3QOjlrBL/tWgecCeApMybMslYbCcx/drc6fN6ilU5iVR+b1+5K4+om2Y7ovHUgIPu0XebbWMrIUyNnxwYqkrBqhDt+Pgkv51vOPekYntwgpKJ/tSVynP9igGXn421YgagAGcIeTExSpdxcUSc0R1yEm0lvCvwgJK/pO7lKvdP8DLnjwNZQkeV80JYDSQqIruZ6WQFNtrrF+Ap90MXZeDqA8c1h+sEb/w/ZRudYlx7v6yhl6vpxS4goZHVFdP02+0JzNKNx/hKQgGjDeL396BgQVgHqHQJmXmDnmCreCvTNNlnw==";
        // 填写Bucket名称，例如examplebucket。
        String bucketName = "2022tiger";
        // 填写Object完整路径，例如exampleobject.txt。Object完整路径中不能包含Bucket名称。
        // 此处请填写多个Object完整路径，用于一次性获取多个Object的签名URL。
        String objectNameList[] = {"exampleobject.txt", "exampleimage.jpg"};

        // 从STS服务获取临时访问凭证后，您可以通过临时访问密钥和安全令牌生成OSSClient。
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret, securityToken);

        try {
            // 设置签名URL过期时间，单位为毫秒。
            Date expiration = new Date(new Date().getTime() + 3600 * 1000);

            List<URL> urlList = new ArrayList<URL>();
            for (int i = 0; i < objectNameList.length; i++) {
                URL url = ossClient.generatePresignedUrl(bucketName, objectNameList[i], expiration);
                urlList.add(url);
            }
            // 打印签名URL。
            for (URL url : urlList) {
                System.out.println("url:" + url);
            }
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

}
