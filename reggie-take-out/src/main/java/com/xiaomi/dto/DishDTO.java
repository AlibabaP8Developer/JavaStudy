package com.xiaomi.dto;

import com.xiaomi.pojo.Dish;
import com.xiaomi.pojo.DishFlavor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * dto用于封装页面提交的数据，数据传输对象，展示层与服务层之间的数据传输
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishDTO extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
