import entities.LegalEntity;
import entities.NaturalPerson;
import entities.Person;
import enumerator.MaritalStatus;

import java.time.LocalDate;

/**
 * @author victor.mello
 */
public class Main {
    public static void main(String[] args) {

        Person naturalPerson = new NaturalPerson(
                "896.498.598.98",
                "John Doe",
                "Rua Augusta, 1234, Consolação, São Paulo - SP, 01305-000, Brasil.",
                LocalDate.of(1990, 3, 20),
                MaritalStatus.Married);

        Person legalEntity = new LegalEntity(
                "72.467.846/0001-47",
                "Enterprise",
                "Rua dos Três Irmãos, 123, Vila Progredior, São Paulo - SP, 05060-030, Brasil.",
                "62.01-5/01 ");

        System.out.println(" ");
        System.out.println("=================================");
        System.out.println("MOD 13 TASK - Abstract class");
        System.out.println("=================================");
        System.out.println(" ");
        System.out.println(naturalPerson.toString());
        System.out.println(" ");
        System.out.println(legalEntity.toString());
    }
}