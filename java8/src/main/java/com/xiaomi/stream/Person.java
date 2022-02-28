package com.xiaomi.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String name;
    private String sex;
    private int age;

    public Person(String name, String sex) {
        this.name = name;
        this.sex = sex;
    }
}
