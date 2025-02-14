import factoryMethodCar.concreteCreator.FactoryConventionalCar;
import factoryMethodCar.concreteCreator.FactoryElectricCar;
import factoryMethodCar.creator.Factory;
import factoryMethodCar.product.Car;

/**
 * @author victor.mello
 */
public class Main {
    public static void main(String[] args) {

        Factory factoryConventionalCar = new FactoryConventionalCar();
        Factory factoryElectricCar = new FactoryElectricCar();

        Car conventionalCar = factoryConventionalCar.factoryMethod();
        Car electricCar = factoryElectricCar.factoryMethod();

        System.out.println(conventionalCar.toString());
        System.out.println(electricCar.toString());

    }
}