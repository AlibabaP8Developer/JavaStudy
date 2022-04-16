package com.xiaomi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomi.common.R;
import com.xiaomi.pojo.Category;
import com.xiaomi.pojo.Employee;
import com.xiaomi.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> saveCategory(@RequestBody Category category) {
        categoryService.save(category);
        return R.success("新增分类成功！");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        // 构造分页构造器
        Page pagInfo = new Page(page, pageSize);
        // 构造条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.orderByDesc(Category::getSort);
        // 执行查询
        categoryService.page(pagInfo, queryWrapper);
        return R.success(pagInfo);
    }

    @DeleteMapping("")
    public R<String> delete(Long ids) {
        categoryService.remove(ids);
        return R.success("分类信息删除成功！");
    }

    @PutMapping("")
    public R<String> update(@RequestBody Category category) {
        categoryService.updateById(category);
        return R.success("修改分类信息成功！");
    }

}
