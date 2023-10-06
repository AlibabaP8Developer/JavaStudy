package com.xiaomi.brake;

public interface BrakeBehavior {
    public void stop();
}

class LongWheelBrake implements BrakeBehavior {
    @Override
    public void stop() {
        System.out.println("模拟长轮胎刹车痕迹");
    }
}

class ShortWheelBrake implements BrakeBehavior {
    @Override
    public void stop() {
        System.out.println("模拟短轮胎刹车痕迹");
    }
}

abstract class Car {
    protected BrakeBehavior wheel;

    public void brake() {
        wheel.stop();
    }
}

class ShortWheelCar extends Car {
    public ShortWheelCar(BrakeBehavior behavior) {
        behavior.stop();
    }
}

class StrategyTest {
    public static void main(String[] args) {
        BrakeBehavior brake = new ShortWheelBrake();
        ShortWheelCar car = new ShortWheelCar(brake);
        car.brake();
    }
}