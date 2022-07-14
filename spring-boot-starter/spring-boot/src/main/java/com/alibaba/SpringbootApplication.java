package com.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootApplication {

    public static void main(String[] args) {
        // 返回ioc容器
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootApplication.class, args);
        // 查看容器里面的组件
//        String[] names = run.getBeanDefinitionNames();
//        for (String name : names) {
//            System.out.println(name);
//        }
        // 从容器中获取组件
//        Pet pet1 = run.getBean("tomcatPet", Pet.class);
//        Pet pet2 = run.getBean("tomcatPet", Pet.class);
//        System.out.println("组件：" + (pet1 == pet2));
//
//        MyConfig bean = run.getBean(MyConfig.class);
//        System.out.println(bean);

        // @Configuration(proxyBeanMethods = true)代理对象调用方法，
        // springboot总会检查这个组件是否在容器中存在
        // 保持组件单实例
//        User user = bean.user01();
//        User user1 = bean.user01();
//        System.out.println(user == user1);

//        User user01 = run.getBean("user01", User.class);
//        Pet tom = run.getBean("tomcatPet", Pet.class);
//        System.out.println("用户的宠物：" + (user01.getPet() == tom));

        System.out.println("======");
        // 获取组件
//        String[] beanNamesForType = run.getBeanNamesForType(User.class);
//        for (String s : beanNamesForType) {
//            System.out.println(s);
//        }

        System.out.println("=======");

        boolean user001 = run.containsBean("user01");
        System.out.println("容器中user001组件：" + user001);

        boolean tomcatPet = run.containsBean("tomcatPet");
        System.out.println("容器中tom组件：" + tomcatPet);

        boolean zhuyuanzhang = run.containsBean("zhuyuanzhang");
        boolean zhuwen = run.containsBean("zhuwen");
        System.out.println("zhuyuanzhang:" + zhuyuanzhang);
        System.out.println("zhuwen:" + zhuwen);
    }

}
