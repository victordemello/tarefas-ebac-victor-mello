package factoryMethodCar.concreteProduct;

import factoryMethodCar.product.Car;

public class ConventionalCar implements Car {

    private Boolean isOn = false;

    @Override
    public void turnOn() {
        this.isOn = true;
    }

    @Override
    public void turnOff() {
        this.isOn = false;
    }

    @Override
    public String toString() {
        return "ConventionalCar{" +
                "isOn=" + isOn +
                '}';
    }
}
