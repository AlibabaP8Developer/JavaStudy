package com.xiaomi;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import java.util.*;
import com.aliyuncs.sts.model.v20150401.*;

public class AssumeRole {

    public static void main(String[] args) {
        //构建一个阿里云客户端，用于发起请求。
        //设置调用者（RAM用户或RAM角色）的AccessKey ID和AccessKey Secret。
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "LTAI5tH1DEGfmGHeMChTf4Mt", "tqgDaBSkVHwSLf2jZagUA4v49IGi1d");
        IAcsClient client = new DefaultAcsClient(profile);

        //构造请求，设置参数。关于参数含义和设置方法，请参见《API参考》。
        AssumeRoleRequest request = new AssumeRoleRequest();
        request.setRegionId("cn-hangzhou");
        request.setRoleArn("acs:ram::1852496147619930:role/aliyunosstokengeneratorrole");
        request.setRoleSessionName("external-username");
        
        //发起请求，并得到响应。
        try {
            AssumeRoleResponse response = client.getAcsResponse(request);
            String s = new Gson().toJson(response);
            System.out.println(s);
            JSON parse = JSONUtil.parse(s);
            System.out.println(parse);
            Map map = parse.toBean(Map.class);
            String credentials = map.get("credentials").toString();
            Object securityToken = JSONUtil.parse(credentials).toBean(Map.class).get("securityToken");
            System.out.println(securityToken);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
        }

    }
}            