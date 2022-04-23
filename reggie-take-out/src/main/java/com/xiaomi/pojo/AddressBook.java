package com.xiaomi.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 地址簿
 */
@Data
@ApiModel("套餐")
public class AddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;


    //用户id
    @ApiModelProperty("用户id")
    private Long userId;


    //收货人
    @ApiModelProperty("收货人")
    private String consignee;


    //手机号
    @ApiModelProperty("手机号")
    private String phone;


    //性别 0 女 1 男
    @ApiModelProperty("性别 0 女 1 男")
    private String sex;


    //省级区划编号
    @ApiModelProperty("省级区划编号")
    private String provinceCode;


    //省级名称
    @ApiModelProperty("省级名称")
    private String provinceName;


    //市级区划编号
    @ApiModelProperty("市级区划编号")
    private String cityCode;


    //市级名称
    @ApiModelProperty("市级区划编号")
    private String cityName;


    //区级区划编号
    @ApiModelProperty("市级区划编号")
    private String districtCode;


    //区级名称
    @ApiModelProperty("区级名称")
    private String districtName;


    //详细地址
    @ApiModelProperty("详细地址")
    private String detail;


    //标签
    @ApiModelProperty("标签")
    private String label;

    //是否默认 0 否 1是
    @ApiModelProperty("是否默认 0 否 1是")
    private Integer isDefault;

    //创建时间
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;


    //更新时间
    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


    //创建人
    @ApiModelProperty("创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;


    //修改人
    @ApiModelProperty("修改人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;


    //是否删除
    @ApiModelProperty("是否删除")
    private Integer isDeleted;
}
