package com.xiaomi.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.google.gson.Gson;
import com.xiaomi.pojo.R;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class VodController {

    //账号AccessKey信息请填写(必选)  LTAI4Fcpmj437CLu3fYVD3gm
    @Value("${aliyun.vod.accessKeyId}")
    private static String accessKeyId;
    //账号AccessKey信息请填写(必选)
    @Value("${aliyun.vod.accessKeySecret}")
    private static String accessKeySecret;
    //STS临时授权方式访问时该参数为必选，使用主账号AccessKey和RAM子账号AccessKey不需要填写
    private static String securityToken;
    //VOD API 的服务接入地址为：vod.{ApiRegion}.aliyuncs.com，{ApiRegion}请替换成对应API接入区域的标识
    private final static String VOD_DOMAIN = "https://vod.cn-shanghai.aliyuncs.com";

    //以下参数不需要修改
    private final static String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    private final static String HTTP_METHOD = "GET";
    private final static String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private final static String UTF_8 = "utf-8";
    private final static Logger LOG = Logger.getLogger(VodController.class.getName());

    /**
     * 阿里云视频点播 公共参数
     * @param FileName 视频文件名称
     * @param Title 视频标题
     * @param Action
     * @param FileSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/vod", method = RequestMethod.GET)
    public R aliyun(String FileName, String Title,
                    String Action, String FileSize) throws Exception {
        //生成私有参数，不同API需要修改
        Map<String, String> privateParams = generatePrivateParamters(Action,FileName,Title,FileSize);
        //生成公共参数，不需要修改
        Map<String, String> publicParams = generatePublicParamters();
        //生成OpenAPI地址，不需要修改
        String URL = generateOpenAPIURL(publicParams, privateParams);
        //String httpGet = httpGet(URL);
        return R.ok().put("URL", URL).put("publicParams", publicParams);
    }

    /**
     * 阿里云视频点播 获取视频播放地址
     * @param VideoId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/aliyunVideoPlayUrl", method = RequestMethod.GET)
    public R aliyunVideoPlayUrl(String VideoId) throws Exception {
        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        String playURL = "";
        try {
            response = getPlayInfo(client, VideoId);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
                playURL = playInfo.getPlayURL();
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
            return R.ok(playURL);
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
        return R.ok();
    }

    /**
     * 阿里云视频点播 获取视频播放凭证
     * @param VideoId
     * @return
     */
    @RequestMapping(value = "/aliyunGetVideoPlayAuth", method = RequestMethod.GET)
    public R aliyunGetVideoPlayAuth(String VideoId) {
        // 创建SubmitMediaInfoJob实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-Shanghai",                // 点播服务所在的地域ID，中国大陆地域请填cn-shanghai
                accessKeyId,        // 您的AccessKey ID
                accessKeySecret );    // 您的AccessKey Secret
        IAcsClient client = new DefaultAcsClient(profile);
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        // 视频ID。可以通过GetVideoList接口查询得到
        request.setVideoId(VideoId);
        try {
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            System.out.println(new Gson().toJson(response));
            System.out.println(response.getPlayAuth());
            String toJson = new Gson().toJson(response);
            String playAuth = response.getPlayAuth();
            return R.ok(playAuth);
        } catch (ServerException e) {
            e.printStackTrace();
            return R.ok();
        } catch (ClientException e) {
            System.out.println("ErrCode:" + e.getErrCode());
            System.out.println("ErrMsg:" + e.getErrMsg());
            System.out.println("RequestId:" + e.getRequestId());
            return R.ok();
        }
    }

    /**
     * 生成视频点播OpenAPI私有参数
     * 不同API需要修改此方法中的参数
     * @return
     */
    private static Map<String, String> generatePrivateParamters(String Action,String FileName,
                                                                String Title,String FileSize) {
        // 接口私有参数列表, 不同API请替换相应参数
        Map<String, String> privateParams = new HashMap<>();
        // 视频ID
        //privateParams.put("VideoId","a1223ff8eccb405db41b201bbf8420d9");
        // API名称  GetVideoPlayAuth CreateUploadVideo
        privateParams.put("Action", Action);
        privateParams.put("FileName", FileName);
        privateParams.put("Title", Title);
        privateParams.put("FileSize", FileSize);
        return privateParams;
    }

    /**
     * 生成视频点播OpenAPI公共参数
     * 不需要修改
     * @return
     */
    private static Map<String, String> generatePublicParamters() {
        Map<String, String> publicParams = new HashMap<>();
        publicParams.put("Format", "JSON");
        publicParams.put("Version", "2017-03-21");
        publicParams.put("AccessKeyId", accessKeyId);
        publicParams.put("SignatureMethod", "HMAC-SHA1");
        publicParams.put("Timestamp", generateTimestamp());
        publicParams.put("SignatureVersion", "1.0");
        publicParams.put("SignatureNonce", generateRandom());
        if (securityToken != null && securityToken.length() > 0) {
            publicParams.put("SecurityToken", securityToken);
        }
        return publicParams;
    }

    /**
     * 生成OpenAPI地址
     * @param privateParams
     * @return
     * @throws Exception
     */
    private static String generateOpenAPIURL(Map<String, String> publicParams, Map<String, String> privateParams) {
        return generateURL(VOD_DOMAIN, HTTP_METHOD, publicParams, privateParams);
    }
    /**
     * @param domain        请求地址
     * @param httpMethod    HTTP请求方式GET，POST等
     * @param publicParams  公共参数
     * @param privateParams 接口的私有参数
     * @return 最后的url
     */
    private static String generateURL(String domain, String httpMethod, Map<String, String> publicParams, Map<String, String> privateParams) {
        List<String> allEncodeParams = getAllParams(publicParams, privateParams);
        String cqsString = getCQS(allEncodeParams);
        out("CanonicalizedQueryString = " + cqsString);
        String stringToSign = httpMethod + "&" + percentEncode("/") + "&" + percentEncode(cqsString);
        out("StringtoSign = " + stringToSign);
        String signature = hmacSHA1Signature(accessKeySecret, stringToSign);
        out("Signature = " + signature);
        return domain + "?" + cqsString + "&" + percentEncode("Signature") + "=" + percentEncode(signature);
    }

    private static List<String> getAllParams(Map<String, String> publicParams, Map<String, String> privateParams) {
        List<String> encodeParams = new ArrayList<String>();
        if (publicParams != null) {
            for (String key : publicParams.keySet()) {
                String value = publicParams.get(key);
                //将参数和值都urlEncode一下。
                String encodeKey = percentEncode(key);
                String encodeVal = percentEncode(value);
                encodeParams.add(encodeKey + "=" + encodeVal);
            }
        }
        if (privateParams != null) {
            for (String key : privateParams.keySet()) {
                String value = privateParams.get(key);
                //将参数和值都urlEncode一下。
                String encodeKey = percentEncode(key);
                String encodeVal = percentEncode(value);
                encodeParams.add(encodeKey + "=" + encodeVal);
            }
        }
        return encodeParams;
    }
    /**
     * 参数urlEncode
     *
     * @param value
     * @return
     */
    private static String percentEncode(String value) {
        try {
            String urlEncodeOrignStr = URLEncoder.encode(value, "UTF-8");
            String plusReplaced = urlEncodeOrignStr.replace("+", "%20");
            String starReplaced = plusReplaced.replace("*", "%2A");
            String waveReplaced = starReplaced.replace("%7E", "~");
            return waveReplaced;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取CQS 的字符串
     *
     * @param allParams
     * @return
     */
    private static String getCQS(List<String> allParams) {
        ParamsComparator paramsComparator = new ParamsComparator();
        Collections.sort(allParams, paramsComparator);
        String cqString = "";
        for (int i = 0; i < allParams.size(); i++) {
            cqString += allParams.get(i);
            if (i != allParams.size() - 1) {
                cqString += "&";
            }
        }
        return cqString;
    }

    private static class ParamsComparator implements Comparator<String> {
        @Override
        public int compare(String lhs, String rhs) {
            return lhs.compareTo(rhs);
        }
    }

    private static String hmacSHA1Signature(String accessKeySecret, String stringtoSign) {
        try {
            String key = accessKeySecret + "&";
            try {
                SecretKeySpec signKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);
                Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
                mac.init(signKey);
                byte[] rawHmac = mac.doFinal(stringtoSign.getBytes());
                //按照Base64 编码规则把上面的 HMAC 值编码成字符串，即得到签名值（Signature）
                return new String(new BASE64Encoder().encode(rawHmac));
            } catch (Exception e) {
                throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
            }
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 生成随机数
     *
     * @return
     */
    private static String generateRandom() {
        String signatureNonce = UUID.randomUUID().toString();
        return signatureNonce;
    }

    /**
     * 生成当前UTC时间戳
     *
     * @return
     */
    public static String generateTimestamp() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

    private static String httpGet(String url) throws Exception {
        CloseableHttpClient httpClient = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet();
            httpGet.setURI(new URI(url));
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(3000)
                    .setSocketTimeout(3000)
                    .build();
            httpGet.setConfig(requestConfig);
            HttpResponse result = httpClient.execute(httpGet);

            String str;
            try {
                /**读取服务器返回过来的json字符串数据**/
                str = EntityUtils.toString(result.getEntity());
                EntityUtils.consume(result.getEntity());
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
            out(str);
            // 这里可以解析视频云点播服务端的响应结果
            return str;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw e;
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                if (httpClient != null)
                    httpClient.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    private static void out(String newLine) {
        System.out.println(newLine);
        LOG.info(newLine);
    }

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    /*获取播放地址函数*/
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client, String VideoId) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(VideoId);
        return client.getAcsResponse(request);
    }

}