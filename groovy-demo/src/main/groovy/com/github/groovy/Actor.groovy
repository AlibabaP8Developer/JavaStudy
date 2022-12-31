package com.github.groovy

class Actor {
    def age;
    def name;// 建议使用def声明方法和属性
    boolean gender;

    /**
     * 不建议指定返回值类型 如String
     * @return
     */
    static def get() {
        return "groovy";
    }

    def getList(name) {
        return name;
    }

    def sale(price) {
        "this is groovy cource price：${price}"
    }

}
