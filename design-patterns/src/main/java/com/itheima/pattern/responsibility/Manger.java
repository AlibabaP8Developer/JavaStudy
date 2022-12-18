package com.itheima.pattern.responsibility;

/**
 * 部门经理类
 */
public class Manger extends Handler {
    public Manger() {
        super(Handler.NUM_ONE, Handler.NUM_THREE);
    }

    @Override
    protected void handlerLeave(LeaveRequest leave) {
        System.out.println(leave.getName() + "请假" + leave.getNumber() + "天，" + leave.getContent());
        System.out.println("部门经理审批：同意");
    }
}
