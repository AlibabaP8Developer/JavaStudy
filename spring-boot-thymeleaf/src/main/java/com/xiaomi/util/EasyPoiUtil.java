package com.xiaomi.util;

import cn.afterturn.easypoi.excel.annotation.Excel;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 动态显示Excel导出列
 *
 * @param <T>
 * @author young
 */
public class EasyPoiUtil<T> {

    /**
     * 需要被反射的对象，使用泛型规范传入对象
     */
    public T t;

    /**
     * 动态更改EasyPoi中控制列显示的值
     *
     * @param columnName 需要转换的列属性名称
     * @param target     默认true
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public void hihdColumn(String columnName, Boolean target) throws Exception {
        if (t == null) {
            throw new ClassNotFoundException("TARGET OBJECT NOT FOUNT");
        }
        if (StringUtils.isEmpty(columnName)) {
            throw new NullPointerException("COLUMN NAME NOT NULL");
        }
        if (target == null) {
            target = true;
        }
        //获取目标对象的属性值
        Field field = t.getClass().getDeclaredField(columnName);
        //获取注解反射对象
        Excel excelAnnon = field.getAnnotation(Excel.class);
        //获取代理
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(excelAnnon);
        Field excelField = invocationHandler.getClass().getDeclaredField("memberValues");
        excelField.setAccessible(true);
        Map memberValues = (Map) excelField.get(invocationHandler);
        memberValues.put("isColumnHidden", target);
    }
}
