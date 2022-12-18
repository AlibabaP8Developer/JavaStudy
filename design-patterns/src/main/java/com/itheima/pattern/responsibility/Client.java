package com.itheima.pattern.responsibility;

public class Client {
    public static void main(String[] args) {
        // 创建请假条对象
        LeaveRequest leaveRequest = new LeaveRequest("太祖爷朱元璋", 1, "龙体欠安");
        // 创建各级领导对象
        GroupLeader groupLeader = new GroupLeader();
        Manger manger = new Manger();
        GeneralManger generalManger = new GeneralManger();
        // 设置处理者链
        generalManger.setNextHandler(manger);
        manger.setNextHandler(generalManger);
        // 朱元璋提交请假申请
        groupLeader.submit(leaveRequest);
    }
}
