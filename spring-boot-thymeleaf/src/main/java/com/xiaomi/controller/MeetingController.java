package com.xiaomi.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.pojo.MeetingBasicInfo;
import com.xiaomi.service.MeetingService;
import com.xiaomi.util.TemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequestMapping("/meeting")
@Controller
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @Value("${filepath}")
    private String filepath;

    @GetMapping("generate")
    @ResponseBody
    public String generate() {
        // 生成静态页面
        generateTemplateList();
        generateTemplateDetail();
        return "会议成功静态页面生成";
    }

    private void generateTemplateDetail() {
        QueryWrapper<MeetingBasicInfo> wrapper = new QueryWrapper<>();
        // 会议信息是否在发展合作平台网站展示(01是,02否)
        wrapper.eq("meeting_is_show", "01");
        wrapper.eq("meeting_yearplan_type_dict", "main_meeting");
        List<MeetingBasicInfo> list = meetingService.list(wrapper);
        for (MeetingBasicInfo meetingBasicInfo : list) {
            //创建Context对象(存放Model)
            Context context = new Context();
            //放入数据
            context.setVariable("meetingName",meetingBasicInfo.getMeetingName());
            context.setVariable("createdate", meetingBasicInfo.getCreateDate());
            context.setVariable("meetingReleaseDate", meetingBasicInfo.getMeetingReleaseDate());
            context.setVariable("content", meetingBasicInfo.getMeetingContent());
            context.setVariable("meetingIntroduction", meetingBasicInfo.getMeetingIntroduction());
            context.setVariable("meetingYicheng", meetingBasicInfo.getMeetingYicheng());
            try {
                TemplateUtil.template("meetingDetail", context,
                        new File(filepath + "/meeting/detail/" + meetingBasicInfo.getMeetingName()+"-"+meetingBasicInfo.getId() + ".html"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void generateTemplateList() {
        int current = 1;
        int size = 10;

        while (true) {
            Page<MeetingBasicInfo> page = new Page<>(current, size);
            QueryWrapper<MeetingBasicInfo> wrapper = new QueryWrapper<>();
            wrapper.eq("meeting_is_show", "01");
            wrapper.eq("meeting_yearplan_type_dict", "main_meeting");
            Page<MeetingBasicInfo> page1 = meetingService.page(page, wrapper);
            int total = (int) page1.getTotal();
            int totalPage = total / size;
            if (totalPage * size < total) {
                totalPage += 1;
            }

            List<MeetingBasicInfo> list = page1.getRecords();
            //创建Context对象(存放Model)
            Context context = new Context();
            //放入数据
            context.setVariable("meetingList", list);
            context.setVariable("meetingCurrent", current);
            context.setVariable("meetingTotal", total);
            context.setVariable("meetingTotalPage", totalPage);

            context.setVariable("meetingPage", page1);
            context.setVariable("meetingJumpUrl", "list");
            try {
                if (list.size() == 0) {
                    break;
                }
                TemplateUtil.template("meetingList", context,
                        new File(filepath + "/meeting/list/list" + current + ".html"));
                current++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
