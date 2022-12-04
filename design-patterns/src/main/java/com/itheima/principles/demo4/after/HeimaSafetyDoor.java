package com.itheima.principles.demo4.after;

/**
 * 黑马安全门
 */
public class HeimaSafetyDoor implements AntiTheft, FireProof, WaterProof {

    @Override
    public void antiTheft() {
        System.out.println("防盗功能");
    }

    @Override
    public void fireProof() {
        System.out.println("防火功能");
    }

    @Override
    public void waterProof() {
        System.out.println("防水功能");
    }
}
