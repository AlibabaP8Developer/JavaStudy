package com.alibaba;

import com.alibaba.bean.Pet;
import com.alibaba.bean.User;
import com.alibaba.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        // 返回ioc容器
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootApplication.class, args);
        // 查看容器里面的组件
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        // 从容器中获取组件
        Pet pet1 = run.getBean("tomcatPet", Pet.class);
        Pet pet2 = run.getBean("tomcatPet", Pet.class);
        System.out.println("组件：" + (pet1 == pet2));

        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);

        // @Configuration(proxyBeanMethods = true)代理对象调用方法，springboot总会检查这个组件是否在容器中存在
        // 保持组件单实例
        User user = bean.user01();
        User user1 = bean.user01();
        System.out.println(user == user1);
    }

}
