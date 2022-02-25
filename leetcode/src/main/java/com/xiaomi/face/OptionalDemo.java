package com.xiaomi.face;

import java.util.Optional;

public class OptionalDemo {
    public static void main(String[] args) {
        Zoo zoo = getZoo();
        if (zoo != null) {
            Dog dog = zoo.getDog();
            if (dog != null) {
                int age = dog.getAge();
                System.out.println(age);
            }
        }
        // Optional
        Optional.ofNullable(zoo).map(o -> o.getDog()).map(d -> d.getAge()).ifPresent(age -> {
            System.out.println("age:" + age);
        });
    }

    static Zoo getZoo() {
        return new Zoo();
    }

    static class Zoo {
        private Dog dog;

        Dog getDog() {
            return new Dog();
        }
    }

    static class Dog {
        private int age;

        int getAge() {
            return age;
        }
    }
}
