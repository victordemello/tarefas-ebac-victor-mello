package entities;

public abstract class Person {

    private String name;
    private String Address;

    public Person(){

    }

    public Person(String name, String address) {
        this.name = name;
        Address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
