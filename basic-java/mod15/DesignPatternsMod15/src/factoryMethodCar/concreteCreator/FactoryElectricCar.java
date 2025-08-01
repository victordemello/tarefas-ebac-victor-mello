package factoryMethodCar.concreteCreator;

import factoryMethodCar.concreteProduct.ElectricCar;
import factoryMethodCar.creator.Factory;
import factoryMethodCar.product.Car;

public class FactoryElectricCar implements Factory {
    @Override
    public Car factoryMethod() {
        return new ElectricCar();
    }
}
