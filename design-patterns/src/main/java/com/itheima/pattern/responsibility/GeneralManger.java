package com.itheima.pattern.responsibility;

/**
 * 总经理类
 */
public class GeneralManger extends Handler {
    public GeneralManger() {
        super(Handler.NUM_ONE, Handler.NUM_SEVEN);
    }

    @Override
    protected void handlerLeave(LeaveRequest leave) {
        System.out.println(leave.getName() + "请假" + leave.getNumber() + "天，" + leave.getContent());
        System.out.println("总经理审批：同意");
    }
}
