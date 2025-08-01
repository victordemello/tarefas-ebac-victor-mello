import person.PersonService;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        PersonService personService = new PersonService(new ArrayList<>(), sc);

        Character resp = 'y';
        while(resp.equals('y')){
            try{
                personService.registerPerson();
            }catch (Exception ex){
                System.err.println("Error: Invalid gender");
                System.err.println("Invalid gender. Only F or M can be selected.");
                System.err.println("Make a new registration, providing the correct gender.");
            }
            System.out.println("===================");
            System.out.println("Do you want to make a new registration? (Y / N)");
            resp = sc.nextLine()
                    .toLowerCase()
                    .charAt(0);
        }

        System.out.println(" ");
        System.out.println("List of registered female persons");
        System.out.println(personService.filterOnlyWomen().toString());
    }
}