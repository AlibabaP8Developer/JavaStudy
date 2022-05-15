package com.xiaomi;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoListRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoListResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class VodTest {

    // 根据Date时间生成UTC时间函数
    public static String generateUTCTime(Date time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateFormat.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
        dateFormat.setLenient(false);
        return dateFormat.format(time);
    }

    /**
     * 获取视频列表
     * @param client 发送请求客户端
     * @return GetVideoListResponse 获取视频列表响应数据
     * @throws Exception
     */
    public static GetVideoListResponse getVideoList(DefaultAcsClient client) throws Exception {
        GetVideoListRequest request = new GetVideoListRequest();
        // 分别取一个月前、当前时间的UTC时间作为筛选视频列表的起止时间
        String monthAgoUTCTime = generateUTCTime(new Date(System.currentTimeMillis() - 30*86400*1000L));
        String nowUTCTime = generateUTCTime(new Date(System.currentTimeMillis()));
        // 视频创建的起始时间，为UTC格式
        request.setStartTime(monthAgoUTCTime);
        // 视频创建的结束时间，为UTC格式
        request.setEndTime(nowUTCTime);
        // 视频状态，默认获取所有状态的视频，多个用逗号分隔
        // request.setStatus("Uploading,Normal,Transcoding");
        request.setPageNo(1);
        request.setPageSize(20);
        return client.getAcsResponse(request);
    }

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    // 请求示例
    public static void main(String[] argv) throws ClientException {
        DefaultAcsClient client = initVodClient("LTAIXF8S3xycEbcP", "m1DQNcYiVeBkp3EDavnd8feXgfp4Fw");
//        DefaultAcsClient client = initVodClient("LTAIXF8S3xycEbcP", "m1DQNcYiVeBkp3EDavnd8feXgfp4Fw");
        GetVideoListResponse response = new GetVideoListResponse();
        try {
            response = getVideoList(client);
            // 根据指定筛选条件查询到的视频总数
            System.out.print("Total = " + response.getTotal() + "\n");
            for (GetVideoListResponse.Video video : response.getVideoList()) {
                System.out.print("Title = " + video.getTitle() + "\n");
                System.out.print("Description = " + video.getDescription() + "\n");
                System.out.print("Tags = " + video.getTags() + "\n");
                System.out.print("CreationTime = " + video.getCreationTime() + "\n");
            }
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }


}
