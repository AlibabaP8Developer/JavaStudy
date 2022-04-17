package com.xiaomi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.common.R;
import com.xiaomi.dto.DishDTO;
import com.xiaomi.pojo.Category;
import com.xiaomi.pojo.Dish;
import com.xiaomi.service.CategoryService;
import com.xiaomi.service.DishFlavorService;
import com.xiaomi.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 新增菜品
     *
     * @param dishDTO
     * @return
     */
    @PostMapping("")
    public R<String> save(@RequestBody DishDTO dishDTO) {
        dishService.saveWithDishFlavor(dishDTO);
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
        return R.success("修改菜品成功！");
    }

    /**
     * 菜品信息的分页
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
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
     * @param id 菜品ID
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDTO> getDishById(@PathVariable("id") Long id) {

        DishDTO dishDTO = dishService.getByIdWithFlavor(id);

        return R.success(dishDTO);
    }
}
