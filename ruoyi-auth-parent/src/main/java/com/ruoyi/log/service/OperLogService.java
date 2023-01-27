package com.ruoyi.log.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ruoyi.dto.vo.SysOperLogQueryVo;
import com.ruoyi.pojo.system.SysOperLog;

public interface OperLogService {

    public void saveSysLog(SysOperLog sysOperLog);

    //操作日志分页查询
    IPage<SysOperLog> selectPage(Long page, Long limit, SysOperLogQueryVo sysOperLogQueryVo);
}
