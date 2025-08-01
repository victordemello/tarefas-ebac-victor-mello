package factoryMethodCar.concreteCreator;

import factoryMethodCar.concreteProduct.ConventionalCar;
import factoryMethodCar.creator.Factory;
import factoryMethodCar.product.Car;

public class FactoryConventionalCar implements Factory {
    @Override
    public Car factoryMethod() {
        return new ConventionalCar();
    }
}
