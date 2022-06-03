package com.xiaomi.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaomi.pojo.EmailTaskDetail;
import com.xiaomi.pojo.MeetingBasicInfo;
import com.xiaomi.pojo.User;
import com.xiaomi.service.MeetingService;
import com.xiaomi.util.EmailUtil;
import com.xiaomi.util.ExcelUtils;
import com.xiaomi.util.FileUtil;
import com.xiaomi.util.QRcodeZxingUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/tool")
public class ToolController {

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private MeetingService meetingBasicInfoService;

    /**
     * 导入主办会议信息示例，具体根据业务场景自行修改
     * @param file
     * @param response
     * @return
     */
    @PostMapping("/importMainMeeting")
    public String importMainMeeting(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        String officeKey = null;
        List<MeetingBasicInfo> list = null;
        List<MeetingBasicInfo> failList = null;
        ExcelImportResult<MeetingBasicInfo> result = null;
        try {
            ImportParams importParams = new ImportParams();
            importParams.setTitleRows(1);
            importParams.setHeadRows(1);
            result = ExcelImportUtil.importExcelMore(file.getInputStream(), MeetingBasicInfo.class, importParams);
            list = result.getList();
            failList = result.getFailList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (CollectionUtils.isEmpty(list)) {
            return "没有有效的数据,导入数据失败";
        }

        String userId = "1";//SysUtils.getActiveUser().getUserId().toString();
        list.forEach(meetingBasicInfo -> {
            QueryWrapper<MeetingBasicInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("meeting_rand", meetingBasicInfo.getMeetingRand());
            List<MeetingBasicInfo> rand = meetingBasicInfoService.list(queryWrapper);
            for (MeetingBasicInfo basicInfo : rand) {
                String random = RandomStringUtils.randomNumeric(3);
                if (basicInfo != null) {
                    String meetingRand = basicInfo.getMeetingRand();
                    if (!random.equals(meetingRand)) {
                        meetingBasicInfo.setMeetingRand(random);
                        break;
                    } else {
                        continue;
                    }
                }
            }

            if (rand.size() == 0) {
                String random = RandomStringUtils.randomNumeric(3);
                meetingBasicInfo.setMeetingRand(random);
            }

            Date meetingStartDate = meetingBasicInfo.getMeetingStartDate();
            if (meetingStartDate != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                String startDateYear = sdf.format(meetingStartDate);
                meetingBasicInfo.setMeetingYear(startDateYear);
            }

            meetingBasicInfo.setId(UUID.randomUUID().toString());
            meetingBasicInfo.setMeetingIsEnroll("02");
            meetingBasicInfo.setMeetingIsShow("02");
            meetingBasicInfo.setMeetingYearplanType("主办会议");
            meetingBasicInfo.setMeetingYearplanTypeDict("main_meeting");
            meetingBasicInfo.setUpdateBy(userId);
            meetingBasicInfo.setCreateBy(userId);

            meetingBasicInfo.setIsShowZhOnsite("Yes");
            meetingBasicInfo.setIsShowZhOnline("Yes");
            meetingBasicInfo.setIsShowEnOnsite("Yes");
            meetingBasicInfo.setIsShowEnOnline("Yes");

            meetingBasicInfo.setIsShowZhOnsiteIntention("No");
            meetingBasicInfo.setIsShowZhOnlineIntention("No");
            meetingBasicInfo.setIsShowEnOnsiteIntention("No");
            meetingBasicInfo.setIsShowEnOnlineIntention("No");
            meetingBasicInfo.setMeetingIsShowEn("02");
            meetingBasicInfoService.save(meetingBasicInfo);
        });

        try {
            ExcelUtils.downLoadVolFailExcel(result, response, MeetingBasicInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "导入成功！";
    }

    /**
     * 导出主办会议信息示例，具体根据业务场景自行修改
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/exportMainMeeting")
    public String exportMainMeeting(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 会议类型
            Map<String, String> allMeetingTypeMap = null;//SysUtils.getDictMap("meeting_type_dict");

            // 部门
            Map<String, String> allMeetingDeptMap = null;//SysUtils.getDictMap("geidco_dept_dict");

            // 会议形式
            Map<String, String> allMeetingFormMap = null;//SysUtils.getDictMap("meeting_main_form_dict");

            // 会议形式 英文
            Map<String, String> allMeetingFormEnMap = null;//SysUtils.getDictMap("meeting_main_form_dict_en");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<MeetingBasicInfo> meetingBasicInfoList = meetingBasicInfoService.list();
            for (MeetingBasicInfo meetingBasicInfo : meetingBasicInfoList) {
                Date meetingStartDate = meetingBasicInfo.getMeetingStartDate();
                String formatStart = sdf.format(meetingStartDate);
                Date parseStart = sdf.parse(formatStart);
                meetingBasicInfo.setMeetingStartDate(parseStart);

                Date meetingEndDate = meetingBasicInfo.getMeetingEndDate();
                String formatEnd = sdf.format(meetingEndDate);
                Date parseEnd = sdf.parse(formatEnd);
                meetingBasicInfo.setMeetingEndDate(parseEnd);

                // 填报部门
                String meetingFillInDeptDict = meetingBasicInfo.getMeetingFillInDeptDict();
                if (StringUtils.isNotBlank(meetingFillInDeptDict)) {
                    for (String s : allMeetingDeptMap.keySet()) {
                        if (meetingFillInDeptDict.equals(s)) {
                            String mdpt = allMeetingDeptMap.get(s);
                            meetingBasicInfo.setMeetingFillInDept(mdpt);
                        }
                    }
                }

                // 牵头部门
                String meetingHeadDeptDict = meetingBasicInfo.getMeetingHeadDeptDict();
                if (StringUtils.isNotBlank(meetingHeadDeptDict)) {
                    for (String s : allMeetingDeptMap.keySet()) {
                        if (meetingHeadDeptDict.equals(s)) {
                            String mhpt = allMeetingDeptMap.get(s);
                            meetingBasicInfo.setMeetingHeadDept(mhpt);
                        }
                    }
                }

                // 会议类型
                String meetingTypeDict = meetingBasicInfo.getMeetingTypeDict();
                if (StringUtils.isNotBlank(meetingTypeDict)) {
                    for (String s : allMeetingTypeMap.keySet()) {
                        if (meetingTypeDict.equals(s)) {
                            String meetingType = allMeetingTypeMap.get(s);
                            meetingBasicInfo.setMeetingType(meetingType);
                        }
                    }
                }

                // 会议形式
                String meetingMainForm = meetingBasicInfo.getMeetingMainFormDict();
                if (StringUtils.isNotBlank(meetingMainForm)) {
                    for (String s : allMeetingFormMap.keySet()) {
                        if (meetingMainForm.equals(s)) {
                            String meetingForm = allMeetingFormMap.get(s);
                            meetingBasicInfo.setMeetingMainForm(meetingForm);
                        }
                    }
                }

                // 会议形式(英文)
                String meetingMainFormDictEn = meetingBasicInfo.getMeetingMainFormDictEn();
                if (StringUtils.isNotBlank(meetingMainFormDictEn)) {
                    for (String s : allMeetingFormEnMap.keySet()) {
                        if (meetingMainFormDictEn.equals(s)) {
                            String meetingFormEn = allMeetingFormEnMap.get(s);
                            meetingBasicInfo.setMeetingMainFormEn(meetingFormEn);
                        }
                    }
                }

            }
            ExcelUtils.exportExcel(meetingBasicInfoList, "主办会议列表", "主办会议列表", MeetingBasicInfo.class, "主办会议", response);
            return "导出成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 下载导入模版示例，具体根据业务场景自行修改
     * @param response
     * @return
     */
    @GetMapping("/downloadTemplate")
    public String downloadTemplate(HttpServletResponse response) {
        try {
            // 组装数据
            List list = new ArrayList();
            ExcelUtils.exportExcel(list, "批量发送邮件信息",
                    "批量发送邮件信息", EmailTaskDetail.class,
                    "批量发送邮件信息", response);
            return "下载成功！";
        } catch (Exception e) {
            return "下载失败！";
        }
    }

    /**
     * 导出文本示例，具体根据业务场景自行修改
     * @param file
     */
    @PostMapping(value = "/excel")
    @ResponseBody
    public void excel(@RequestParam("file") MultipartFile file) {
        List<User> list = null;
        ExcelImportResult<User> result = null;

        try {
            ImportParams importParams = new ImportParams();
            importParams.setTitleRows(1);
            importParams.setHeadRows(1);
            result = ExcelImportUtil.importExcelMore(file.getInputStream(), User.class, importParams);
            list = result.getList();
            StringBuilder stringBuilder = new StringBuilder();
            list.forEach(demo -> {
                // 根据业务需求改
                System.out.println(demo);
                String email = demo.getEmail();
                String id = demo.getId();
                String str = "UPDATE auth_user SET USER_EMAIL = '" + email + "' WHERE USER_ID = '" + id + "';";
                stringBuilder.append(str).append("\n");
            });
            File file1 = new File("C:\\Users\\baidu\\Desktop" + File.separator + "excelSql.sql");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }

            Writer out = null;
            out = new FileWriter(file1);
            out.write(stringBuilder.toString());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件操作
     *
     * @param info      输入要追加的指定内容
     * @param replaceTo 替换的内容
     * @return
     */
    @RequestMapping("/file")
    public String file(String info, String replaceTo) {
        if (StringUtils.isBlank(replaceTo)) {
            FileUtil.fileNameAddTo(new File("/Users/lizhenghang/Desktop"), info);
        } else {
            FileUtil.fileNameManage(new File("/Users/lizhenghang/Desktop"), info, replaceTo);
        }
        return "ok";
    }

    /**
     * 生成二维码
     * @param info 二维码信息
     * @param width 二维码宽度
     * @param height 二维码高度
     * @param response 下载
     */
    @GetMapping("/qrcode")
    public void qrcode(String info, int width, int height, HttpServletResponse response) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"));
        String path = QRcodeZxingUtil.generateQRcodePic(info, width, height, "jpg", new File(""));
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=" + format + ".jpg");
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            File ftp = ResourceUtils.getFile(path);
            InputStream in = new FileInputStream(ftp);

            // 循环取出流中的数据
            byte[] b = new byte[100];
            int len;
            while ((len = in.read(b)) != -1) {
                out.write(b, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发邮件
     */
    @RequestMapping("/sendMail")
    public String sendMail(@RequestBody Map<String, String> info) {
        String host = info.get("host");
        String password = info.get("password");
        String title = info.get("title");
        String content = info.get("content");
        String from = info.get("from");
        String[] to = info.get("to").split(",");

        if (StringUtils.isNotBlank(from)) {
            EmailUtil.sendMail(from, to, host, password, title, content, new ArrayList<>());
        } else {
            EmailUtil.sendNoFromMail(to, title, content, new ArrayList<File>(), mailSender);
        }
        return "ok";
    }

}
