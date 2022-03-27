package com.xiaomi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomi.pojo.MeetingBasicInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MeetingDao extends BaseMapper<MeetingBasicInfo> {

}
