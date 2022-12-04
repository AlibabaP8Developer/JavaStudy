package com.itheima.principles.demo4.after;

/**
 * 接口隔离原则
 */
public class Client {
    public static void main(String[] args) {
        HeimaSafetyDoor door = new HeimaSafetyDoor();
        door.antiTheft();
        door.fireProof();
        door.waterProof();

        System.out.println("======");

        ItcastSafetyDoor itcastSafetyDoor = new ItcastSafetyDoor();
        itcastSafetyDoor.antiTheft();
        itcastSafetyDoor.fireProof();
    }
}
