package com.xiaomi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaomi.dto.DishDTO;
import com.xiaomi.pojo.Dish;

public interface DishService extends IService<Dish> {

    /**
     * 新增菜品，同时插入菜品对应的口味数据，需要操作两张表:dish/dish_flavor
     */
    void saveWithDishFlavor(DishDTO dishDTO);

    /**
     * 根据ID查询菜品信息和对应的口味信息
     * @param id 菜品ID
     * @return
     */
    DishDTO getByIdWithFlavor(Long id);

    /**
     * 更新菜品信息，同时更新对应的口味信息
     * @param dishDTO
     */
    void updateWithDishFlavor(DishDTO dishDTO);
}
