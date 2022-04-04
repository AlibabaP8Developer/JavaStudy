package com.xiaomi.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserEnum {
    MAlE(1, "男"),
    FEMAlE(0, "女");

    @EnumValue // 将注解所标识的属性的值存储
    private Integer sex;
    private String sexName;
}
