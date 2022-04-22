package com.xiaomi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.common.R;
import com.xiaomi.dto.SetmealDTO;
import com.xiaomi.pojo.Category;
import com.xiaomi.pojo.Setmeal;
import com.xiaomi.service.CategoryService;
import com.xiaomi.service.SetmealDishService;
import com.xiaomi.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 套餐管理
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        // 分页构造器
        Page<Setmeal> pageInfo = new Page<>(page, pageSize);
        Page<SetmealDTO> setmealDTOPage = new Page<>();

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        // 添加查询条件，根据name进行like模糊查询
        queryWrapper.like(StringUtils.isNotBlank(name), Setmeal::getName, name);
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        // 执行分页查询
        setmealService.page(pageInfo, queryWrapper);

        // 对象拷贝
        BeanUtils.copyProperties(pageInfo, setmealDTOPage, "records");

        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDTO> list = records.stream().map(item -> {
            SetmealDTO setmealDTO = new SetmealDTO();
            // 对象拷贝
            BeanUtils.copyProperties(item, setmealDTO);

            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category != null) {
                String categoryName = category.getName();
                setmealDTO.setCategoryName(categoryName);
            }

            return setmealDTO;
        }).collect(Collectors.toList());

        setmealDTOPage.setRecords(list);
        return R.success(setmealDTOPage);
    }

    @PostMapping("")
    @CacheEvict(value = "setmealCache", allEntries = true)
    public R<String> save(@RequestBody SetmealDTO setmealDTO) {
        setmealService.saveWithDish(setmealDTO);
        return R.success("保存套餐成功！");
    }

    @PutMapping("")
    @CacheEvict(value = "setmealCache", allEntries = true)
    public R<String> update(@RequestBody SetmealDTO setmealDTO) {
        setmealService.updateWithDish(setmealDTO);
        return R.success("保存套餐成功！");
    }

    /**
     * 删除套餐
     *
     * @param ids 套餐ID
     * @return
     */
    @DeleteMapping("")
    // 清理setmealCache分类下的所有缓存数据
    @CacheEvict(value = "setmealCache", allEntries = true)
    public R<String> delete(@RequestParam(value = "ids") List<Long> ids) {
        setmealService.removeWithDish(ids);
        return R.success("删除套餐成功！");
    }

    /**
     * 停止/开启售卖套餐
     *
     * @param ids
     */
    @PostMapping("/status/{type}")
    public R<String> stop(@RequestParam(value = "ids") List<Long> ids, @PathVariable("type") int type) {
        setmealService.stop(ids, type);
        return R.success("停止/开启售卖套餐成功！");
    }

    @GetMapping("/{id}")
    public R<SetmealDTO> get(@PathVariable("id") Long id) {
        SetmealDTO setmealDTO = setmealService.getByIdWithDish(id);
        return R.success(setmealDTO);
    }

    /**
     * 根据条件查询套餐数据
     *
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    @Cacheable(value = "setmealCache", key = "#setmeal.categoryId+'_'+#setmeal.status")
    public R<List<Setmeal>> list(Setmeal setmeal) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(setmeal.getCategoryId() != null, Setmeal::getCategoryId, setmeal.getCategoryId());
        queryWrapper.eq(setmeal.getStatus() != null, Setmeal::getStatus, setmeal.getStatus());
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        List<Setmeal> list = setmealService.list(queryWrapper);
        return R.success(list);
    }
}
