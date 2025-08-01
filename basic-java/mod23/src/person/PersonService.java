package person;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public final class PersonService {
    List<Person> personList;
    private Scanner sc;

    public PersonService(){

    }

    public PersonService(List<Person> personList, Scanner sc){
        this.personList = personList;
        this.sc = sc;
    }
    public PersonService(List<Person> personList){
        this.personList = personList;
    }

    public void registerPerson() throws Exception{
        System.out.println("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter gender (M, F): ");
        Character gender = sc.nextLine().charAt(0);

        if(!validateGender(gender)){
            throw new Exception();
        }

        personList.add(new Person(name, gender));
    }

    public void registerPerson(String name, Character gender) {

        personList.add(new Person(name, gender));
    }

    public List<Person> filterOnlyWomen(){
        return personList.stream()
                .filter(p -> p.getGender().equals('f'))
                .collect(Collectors.toList());
    }

    private boolean validateGender(Character gender){
        return gender != null && (gender.equals('m') || gender.equals('f'));
    }

    @Override
    public String toString() {
        return "PersonRepository{" +
                "personList=" + personList +
                '}';
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
