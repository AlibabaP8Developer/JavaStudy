package com.github.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DbConstant {
    public static final String HOST = "139.198.181.54";
    public static final Integer PORT = 3306;
    public static final String DATABASE_NAME = "jeecg-nacos";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";
    public static final String PATH = "/Users/lizhenghang/Desktop/sqldump/";
    public static final String FILE_NAME = "dump"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +".sql";
    public static final String FILE_NAME_ALL = "dump-all-table"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +".sql";
    public static final String FILE_NAME_JOB = "dump-job"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) +".sql";
}
