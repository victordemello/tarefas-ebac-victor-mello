package entities;

import enumerator.MaritalStatus;

import java.time.LocalDate;

public class NaturalPerson extends Person{
    private String cpf;
    private LocalDate dateOfBirth;
    private MaritalStatus maritalStatus;

    public NaturalPerson(){

    }

    public NaturalPerson(String cpf, String name, String address, LocalDate dateOfBirth, MaritalStatus maritalStatus) {
        super(name, address);
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.maritalStatus = maritalStatus;
    }

    @Override
    public String toString() {
        return "NaturalPerson{" +
                "name='" + super.getName() + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", maritalStatus=" + maritalStatus +
                ", address='" + super.getAddress() + '\'' +
                '}';
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }
}
