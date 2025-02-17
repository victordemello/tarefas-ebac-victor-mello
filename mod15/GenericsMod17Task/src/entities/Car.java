package entities;

import java.util.Objects;

public abstract class Car {

    private String model;
    private Boolean isRunning;

    protected Car(String model){
        this.model = model;
        this.isRunning = false;
    }

    protected void turnOn(){
        this.isRunning = true;
    }

    protected void turnOff(){
        this.isRunning = false;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getRunning() {
        return isRunning;
    }

    public void setRunning(Boolean running) {
        isRunning = running;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", isRunning=" + isRunning +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(model, car.model) && Objects.equals(isRunning, car.isRunning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, isRunning);
    }
}
