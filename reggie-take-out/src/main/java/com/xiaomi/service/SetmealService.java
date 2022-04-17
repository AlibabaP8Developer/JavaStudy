package com.xiaomi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.dto.SetmealDTO;
import com.xiaomi.pojo.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     * @param setmealDTO
     */
    void saveWithDish(SetmealDTO setmealDTO);

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */
    void removeWithDish(List<Long> ids);

    /**
     * 停止/开启售卖套餐
     * @param ids
     */
    void stop(List<Long> ids, int type);

    SetmealDTO getByIdWithDish(Long id);

    void updateWithDish(SetmealDTO setmealDTO);
}
