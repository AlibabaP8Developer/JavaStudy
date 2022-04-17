package com.xiaomi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.dao.DishDAO;
import com.xiaomi.dto.DishDTO;
import com.xiaomi.pojo.Dish;
import com.xiaomi.pojo.DishFlavor;
import com.xiaomi.service.DishFlavorService;
import com.xiaomi.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishDAO, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 新增菜品，同时保存对应的口味数据
     *
     * @param dishDTO
     */
    @Override
    @Transactional
    public void saveWithDishFlavor(DishDTO dishDTO) {
        // 保存菜品基本信息dish
        this.save(dishDTO);
        // 菜品ID
        Long dishId = dishDTO.getId();

        // 菜品口味
        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors = flavors.stream().map(item -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        // 保存菜品口味数据到菜品口味表dish_flavor
        dishFlavorService.saveBatch(flavors);
    }

    /**
     * 根据ID查询菜品信息和对应的口味信息
     *
     * @param id 菜品ID
     * @return
     */
    @Override
    public DishDTO getByIdWithFlavor(Long id) {
        // 查询菜品基本信息，dish
        Dish dish = this.getById(id);

        // 查询菜品口味信息，dish_flavor
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, id);
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);

        DishDTO dishDTO = new DishDTO();
        BeanUtils.copyProperties(dish, dishDTO);

        dishDTO.setFlavors(flavors);
        return dishDTO;
    }

    /**
     * 更新菜品信息，同时更新对应的口味信息
     *
     * @param dishDTO
     */
    @Override
    public void updateWithDishFlavor(DishDTO dishDTO) {
        // 更新dish表基本信息
        this.updateById(dishDTO);
        // 清理当前菜品对应口味数据
        Long dishDTOId = dishDTO.getId();
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dishDTOId);
        dishFlavorService.remove(queryWrapper);
        // 添加当前提交过来的口味数据
        List<DishFlavor> flavors = dishDTO.getFlavors();
        flavors = flavors.stream().map(item -> {
            item.setDishId(dishDTOId);
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
    }
}
