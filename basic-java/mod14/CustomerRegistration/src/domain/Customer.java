package domain;

import java.util.Objects;

/**
 * @author victor.mello
 * */
public class Customer {
    private String name;
    private Long cpf;
    private Long telephone;
    private String address;
    private Integer number;
    private String city;
    private String state;

    public Customer(String name, Long cpf, Long telephone, String address, Integer number, String city, String state) {
        this.name = name;
        this.cpf = cpf;
        this.telephone = telephone;
        this.address = address;
        this.number = number;
        this.city = city;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public Long getTelephone() {
        return telephone;
    }

    public void setTelephone(Long telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(cpf, customer.cpf) && Objects.equals(telephone, customer.telephone) && Objects.equals(address, customer.address) && Objects.equals(number, customer.number) && Objects.equals(city, customer.city) && Objects.equals(state, customer.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cpf, telephone, address, number, city, state);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", cpf=" + cpf +
                ", telephone=" + telephone +
                ", address='" + address + '\'' +
                ", number=" + number +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
