package com.xiaomi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.common.CustomException;
import com.xiaomi.dao.CategoryDAO;
import com.xiaomi.pojo.Category;
import com.xiaomi.pojo.Dish;
import com.xiaomi.pojo.Setmeal;
import com.xiaomi.service.CategoryService;
import com.xiaomi.service.DishService;
import com.xiaomi.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDAO, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据ID删除分类
     * @param id
     */
    @Override
    public void remove(Long id) {
        // 查询当前分类是否关联了菜品，如果已经关联，抛出d异常
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count = dishService.count(dishLambdaQueryWrapper);
        if (count > 0) {
            // 已经关联
            throw new CustomException("当前分类下关联了菜品，不能删除！");
        }
        // 查询当前分类是否关联了套餐，如果已经关联，抛出异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count1 = setmealService.count(setmealLambdaQueryWrapper);
        if (count1 > 0) {
            // 已经关联
            throw new CustomException("当前分类下关联了套餐，不能删除！");
        }
        // 正常删除
        super.removeById(id);
    }
}
