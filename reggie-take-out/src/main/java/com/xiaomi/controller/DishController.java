package com.xiaomi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.common.R;
import com.xiaomi.dto.DishDTO;
import com.xiaomi.pojo.Category;
import com.xiaomi.pojo.Dish;
import com.xiaomi.pojo.DishFlavor;
import com.xiaomi.service.CategoryService;
import com.xiaomi.service.DishFlavorService;
import com.xiaomi.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Api(tags = "菜品管理")
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品
     *
     * @param dishDTO
     * @return
     */
    @PostMapping("")
    public R<String> save(@RequestBody DishDTO dishDTO) {
        dishService.saveWithDishFlavor(dishDTO);
        Long categoryId = dishDTO.getCategoryId();
        String key = "dish_" + categoryId + "_" + dishDTO.getStatus();
        redisTemplate.delete(key);
        return R.success("保存菜品成功！");
    }

    /**
     * 修改菜品
     *
     * @param dishDTO
     * @return
     */
    @PutMapping("")
    public R<String> update(@RequestBody DishDTO dishDTO) {
        dishService.updateWithDishFlavor(dishDTO);
        // 清理所有菜品缓存数据
        Set keys = redisTemplate.keys("dish_*");
        redisTemplate.delete(keys);
        return R.success("修改菜品成功！");
    }

    @DeleteMapping("")
    public R<String> delete(String ids) {
        dishService.removeById(ids);
        return R.success("删除菜品成功！");
    }

    /**
     * 菜品信息的分页
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @ApiOperation(value = "菜品信息的分页查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required=true),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required=true),
            @ApiImplicitParam(name = "name", value = "套餐名称", required=false)
    })
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        // 构造分页构造器对象
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDTO> dishDtoPage = new Page<>();

        // 条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), Dish::getName, name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        // 执行分页查询
        dishService.page(pageInfo, queryWrapper);

        // 对象拷贝
        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");
        List<Dish> records = pageInfo.getRecords();

        List<DishDTO> list = records.stream().map(item -> {
            DishDTO dishDTO = new DishDTO();
            BeanUtils.copyProperties(item, dishDTO);

            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            String categoryName = category.getName();
            dishDTO.setCategoryName(categoryName);

            return dishDTO;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);
        return R.success(dishDtoPage);
    }

    /**
     * 根据ID查询菜品信息和对应的口味信息
     *
     * @param id 菜品ID
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDTO> getDishById(@PathVariable("id") Long id) {

        DishDTO dishDTO = dishService.getByIdWithFlavor(id);

        return R.success(dishDTO);
    }

    /**
     * 根据条件查询对应的菜品数据
     *
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public R<List<DishDTO>> list1(Dish dish) {
        List<DishDTO> dishDTOList = null;

        // 动态构造key
        String key = "dish_" + dish.getCategoryId() + "_" + dish.getStatus();

        // 先从redis中获取缓存数据
        dishDTOList = (List<DishDTO>) redisTemplate.opsForValue().get(key);
        if (dishDTOList != null) {
            // 如果存在，直接返回，无需查询数据库
            return R.success(dishDTOList);
        }

        Long categoryId = dish.getCategoryId();

        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(categoryId != null, Dish::getCategoryId, categoryId);
        // 查询状态为1（在售状态的）
        queryWrapper.eq(Dish::getStatus, 1);
        queryWrapper.orderByDesc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(queryWrapper);

        dishDTOList = list.stream().map(item -> {
            DishDTO dishDTO = new DishDTO();
            BeanUtils.copyProperties(item, dishDTO);

            Long categoryIds = item.getCategoryId();
            Category category = categoryService.getById(categoryIds);
            String categoryName = category.getName();
            dishDTO.setCategoryName(categoryName);

            // 菜品ID
            Long dishId = item.getId();
            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper();
            lambdaQueryWrapper.eq(DishFlavor::getDishId, dishId);
            List<DishFlavor> dishFlavorList = dishFlavorService.list(lambdaQueryWrapper);
            dishDTO.setFlavors(dishFlavorList);
            return dishDTO;
        }).collect(Collectors.toList());
        // 如果不存在，需要查询数据库，将查询到的菜品数据存入redis
        redisTemplate.opsForValue().set(key, dishDTOList, 1, TimeUnit.DAYS);
        return R.success(dishDTOList);
    }

    @Deprecated
    public R<List<Dish>> list(Dish dish) {
        Long categoryId = dish.getCategoryId();

        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(categoryId != null, Dish::getCategoryId, categoryId);
        // 查询状态为1（在售状态的）
        queryWrapper.eq(Dish::getStatus, 1);
        queryWrapper.orderByDesc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(queryWrapper);
        return R.success(list);
    }
}
