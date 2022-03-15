package com.alibaba.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@ToString
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String userName;
    private int age;
    private boolean boss;
    private String[] interests;
    private String phone;
    private Date birthday;
    private Pet pet;
    private List<String> animal;
    private Map<String,Object> score;
    private Set<Double> salarys;
    private Map<String, List<Pet>> allPets;
}
