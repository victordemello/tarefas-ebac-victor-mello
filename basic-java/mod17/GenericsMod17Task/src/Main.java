import entities.BMW;
import entities.Car;
import entities.Lamborghini;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Car> carsList = new ArrayList<>();

        Car car1 = new BMW("BMW X6");
        Car car2 = new Lamborghini("Huracan");
        Car car3 = new BMW("BMW X5");

        carsList.add(car1);
        carsList.add(car2);
        carsList.add(car3);

        System.out.println(carsList.toString());
    }
}