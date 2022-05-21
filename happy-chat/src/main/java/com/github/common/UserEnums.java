package com.github.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum UserEnums {

    LOCK(0, "用户名已禁用！"),
    UNLOCK(1, "用户名正常！"),
    NOTPASSWORD(2, "用户名或密码错误！");

    int code;
    String msg;

}
