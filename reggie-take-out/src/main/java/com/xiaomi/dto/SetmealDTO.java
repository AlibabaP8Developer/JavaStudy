package com.xiaomi.dto;

import com.xiaomi.pojo.Setmeal;
import com.xiaomi.pojo.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDTO extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
