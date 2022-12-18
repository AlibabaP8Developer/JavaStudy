package com.itheima.pattern.responsibility;

/**
 * 小组长类
 */
public class GroupLeader extends Handler {
    public GroupLeader() {
        super(0, Handler.NUM_ONE);
    }

    @Override
    protected void handlerLeave(LeaveRequest leave) {
        System.out.println(leave.getName() + "请假" + leave.getNumber() + "天，" + leave.getContent());
        System.out.println("小组长审批：同意");
    }
}
