package com.github.sharding.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("blog")
@Data
public class User {
    private Integer id;
    private String title;
    private Integer status;
}
