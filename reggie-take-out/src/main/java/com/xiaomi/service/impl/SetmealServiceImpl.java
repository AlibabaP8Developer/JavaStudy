package com.xiaomi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaomi.common.CustomException;
import com.xiaomi.dao.SetmealDAO;
import com.xiaomi.dto.SetmealDTO;
import com.xiaomi.pojo.Setmeal;
import com.xiaomi.pojo.SetmealDish;
import com.xiaomi.service.SetmealDishService;
import com.xiaomi.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealDAO, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 新增套餐，同时需要保存套餐和菜品的关联关系
     *
     * @param setmealDTO
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDTO setmealDTO) {
        // 保存套餐基本信息，Setmeal，insert
        this.save(setmealDTO);
        Long setmealId = setmealDTO.getId();
        // 保存套餐和菜品的关联信息，SetmealDish，insert
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map(item -> {
            item.setSetmealId(setmealId);
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     *
     * @param ids
     */
    @Override
    @Transactional
    public void removeWithDish(List<Long> ids) {
        // 查询套餐状态，确定是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        // 售卖中
        queryWrapper.eq(Setmeal::getStatus, 1);
        int count = this.count(queryWrapper);
        // 如果不能删除，抛出一个异常
        if (count > 0) {
            throw new CustomException("套餐正在售卖中，不能删除！");
        }
        // 如果可以删除，先删除套餐数据，setmeal
        this.removeByIds(ids);
        // 删除关系表中的数据
        // delete from setmeal_dish where setmeal_id in ('setmeal_id')
        LambdaQueryWrapper<SetmealDish> setmealDishQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishQueryWrapper.in(SetmealDish::getSetmealId, ids);
        setmealDishService.remove(setmealDishQueryWrapper);
    }

    /**
     * 停止/开启售卖套餐
     *
     * @param ids
     */
    @Override
    public void stop(List<Long> ids, int type) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId, ids);
        List<Setmeal> list = this.list(queryWrapper);
        list = list.stream().map(item -> {
            item.setStatus(type);
            this.updateById(item);
            return item;
        }).collect(Collectors.toList());
    }

    @Override
    public SetmealDTO getByIdWithDish(Long id) {
        // 查询套餐基本信息
        Setmeal setmeal = this.getById(id);
        LambdaQueryWrapper<SetmealDish> setmealDishQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishQueryWrapper.eq(SetmealDish::getSetmealId, id);

        List<SetmealDish> setmealDishes = setmealDishService.list(setmealDishQueryWrapper);
        SetmealDTO setmealDTO = new SetmealDTO();
        BeanUtils.copyProperties(setmeal, setmealDTO);
        setmealDTO.setSetmealDishes(setmealDishes);
        return setmealDTO;
    }

    @Override
    public void updateWithDish(SetmealDTO setmealDTO) {
        // 更新套餐基本信息
        this.updateById(setmealDTO);
        // 清理当前套餐对应菜品数据
        Long setmealDTOId = setmealDTO.getId();
        LambdaQueryWrapper<SetmealDish> setmealDishQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishQueryWrapper.eq(SetmealDish::getSetmealId, setmealDTOId);
        setmealDishService.remove(setmealDishQueryWrapper);

        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map(item -> {
            item.setSetmealId(setmealDTOId);
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
    }
}
