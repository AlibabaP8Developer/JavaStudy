package com.itheima.pattern.responsibility;

/**
 * 请假条类
 */
public class LeaveRequest {

    // 姓名
    private String name;
    // 请假天数
    private int number;
    // 请假内容
    private String content;

    public LeaveRequest(String name, int number, String content) {
        this.name = name;
        this.number = number;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getContent() {
        return content;
    }
}
