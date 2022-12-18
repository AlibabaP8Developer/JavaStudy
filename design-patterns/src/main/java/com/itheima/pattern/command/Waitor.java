package com.itheima.pattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务员类:属于请求者角色
 */
public class Waitor {

    // 持有多个命令对象
    private List<Command> commands = new ArrayList<>();

    public void setCommand(Command command) {
        // 将command存储到list中
        commands.add(command);
    }

    // 发起命令功能
    public void orderUp() {
        System.out.println("服务员说大厨新订单来了");
        // 遍历list
        for (Command command : commands) {
            if (command != null) {
                command.execute();
            }
        }
    }

}
