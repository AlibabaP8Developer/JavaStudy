package com.github.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DbConstant {
    // 数据表（可选）多表可逗号拼接
    public static final String TABLE_NAME = "";//""ces_order_goods,ces_order_main,ces_shop_type,ces_shop_goods";
    public static final String HOST = "139.198.181.54";
    public static final Integer PORT = 3306;
    public static final String DATABASE_NAME = "jeecg-boot";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";
    // 数据库备份路径
    public static final String PATH = "/Users/lizhenghang/Desktop/sqldump/";
    public static final String FILE_NAME_ALL = "dump-all-table"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +".sql";
    public static final String FILE_NAME = "dump-simple-table"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +".sql";
}
