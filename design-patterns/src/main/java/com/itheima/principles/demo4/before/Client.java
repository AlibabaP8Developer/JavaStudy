package com.itheima.principles.demo4.before;

/**
 * 接口隔离原则
 */
public class Client {
    public static void main(String[] args) {
        SafetyDoor door = new HeimaSafetyDoor();
        door.antiTheft();
        door.fireProof();
        door.waterProof();
    }
}
