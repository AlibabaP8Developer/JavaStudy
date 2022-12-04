package com.itheima.principles.demo4.after;

public class ItcastSafetyDoor implements AntiTheft, FireProof {
    @Override
    public void antiTheft() {
        System.out.println("防盗功能");
    }

    @Override
    public void fireProof() {
        System.out.println("防火功能");
    }
}
