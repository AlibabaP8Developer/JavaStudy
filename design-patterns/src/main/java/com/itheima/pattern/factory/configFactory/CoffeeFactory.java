package com.itheima.pattern.factory.configFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class CoffeeFactory {

    /*
         加载配置文件，获取配置文件中配置的全类名，并创建该类的的对象进行存储
         1.定义容器对象存储咖啡对象
     */
    private static HashMap<String, Coffee> map = new HashMap<>();

    // 2.加载配置文件，只需加载一次
    static {
        // 创建properties对象
        Properties properties = new Properties();
        // 调用p对象中的load方法进行配置文件的加载
        InputStream stream = CoffeeFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        try {
            properties.load(stream);
            // 从p集合中获取全类名并创建对象
            Set<Object> keys = properties.keySet();
            for (Object key : keys) {
                String className = properties.getProperty(key.toString());
                // 通过反射技术创建对象
                Class clazz = Class.forName(className);
                Coffee coffee = (Coffee) clazz.newInstance();
                // 将名称 和 对象存储到容器中
                map.put(key.toString(), coffee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据名称获取对象
     * @param name
     * @return
     */
    public static Coffee createCoffee(String name) {
        return map.get(name);
    }
}
