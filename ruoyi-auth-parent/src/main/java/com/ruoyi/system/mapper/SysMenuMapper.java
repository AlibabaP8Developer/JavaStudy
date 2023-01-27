package com.ruoyi.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.pojo.system.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2022-09-29
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    //根据userid查找菜单权限数据
    List<SysMenu> findMenuListUserId(@Param("userId") String userId);
}
