package com.itheima.principles.demo3.after;

public class ComputerDemo {

    public static void main(String[] args) {
        // 创建计算机的组件对象
        Cpu cpu = new IntelCpu();
        HardDisk hardDisk = new XiJieHardDisk();
        Memory memory = new KingstonMemory();

        // 创建计算机对象
        Computer computer = new Computer();
        computer.setCpu(cpu);
        computer.setMemory(memory);
        computer.setHardDisk(hardDisk);
        computer.run();
    }
}
