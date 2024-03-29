package com.github.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DbConstant {
    // 数据表（可选）可指定多表备份  多表用逗号拼接；默认为""，备份整个数据库所有表
    public static final String TABLE_NAME = "";//"ces_order_goods,ces_order_main,ces_shop_type,ces_shop_goods";
    public static final String HOST = "139.198.181.54";
    public static final String DATABASE_NAME = "jeecg-boot";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";
    // 数据库备份保存路径
    public static final String MAC_PATH = "/Users/lizhenghang/Desktop/sqldump/";
    public static final String WINDOWS_PATH = "d:/commons/docx/sqldump/";
    public static final String LINUX_PATH = "/commons/docx//sqldump/";
    // 数据库备份文件名
    public static final String FILE_NAME_ALL = "dump-table-" + DATABASE_NAME + "-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmm")) + ".sql";
}