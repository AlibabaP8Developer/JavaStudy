package com.alibaba.config;

import com.alibaba.bean.Pet;
import com.alibaba.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
// 配置类本身也是组件
// proxyBeanMethods：代理bean的方法
@Configuration(proxyBeanMethods = true) // 这是一个配置类==配置文件
public class MyConfig {

    /**
     * 给容器中添加组件，以方法名作为组件的ID，返回类型就是组件类型，
     * 返回的值，就是组件在容器中的实例
     * 外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象
     * @return
     */
    @Bean
    public User user01() {
        return new User("李世民", 19);
    }

    @Bean
    public Pet tomcatPet() {
        return new Pet("xiaomao");
    }

}
