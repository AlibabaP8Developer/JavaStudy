package com.itheima.principles.demo3.before;

/**
 * 依赖倒置原则
 *
 * 本案例：违反了开闭原则
 * CPU只能用Intel，内存条只能用金士顿，如果想更换CPU需要修改代码
 *
 * 根据依赖倒转原则进行改进：
 * 代码我们只需要修改Computer类，让Computer类依赖抽象（各个配件的接口），而不是依赖于各个组件具体的实现类。
 */
public class ComputerDemo {

    public static void main(String[] args) {
        // 创建组件对象
        XiJieHardDisk hardDisk  = new XiJieHardDisk();
        IntelCpu cpu = new IntelCpu();
        KingstonMemory memory = new KingstonMemory();

        Computer computer = new Computer();
        computer.setCpu(cpu);
        computer.setHardDisk(hardDisk);
        computer.setMemory(memory);

        computer.run();
    }
}
