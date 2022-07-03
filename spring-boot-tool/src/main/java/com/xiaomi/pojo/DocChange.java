package com.xiaomi.pojo;

public interface DocChange<T extends BaseParams> {

    String doJob(T t);
}