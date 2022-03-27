package com.xiaomi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xiaomi.dao.MeetingDao;
import com.xiaomi.pojo.MeetingBasicInfo;
import com.xiaomi.service.MeetingService;
import org.springframework.stereotype.Service;

@Service
public class MeetingServiceImpl extends ServiceImpl<MeetingDao, MeetingBasicInfo> implements MeetingService {
}