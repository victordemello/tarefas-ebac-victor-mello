package test.person;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import person.Person;
import person.PersonService;

import java.util.ArrayList;
import java.util.List;

public class PersonServiceTest {

    @Test
    public void filterOnlyWomen(){
        PersonService personService = new PersonService(new ArrayList<>());

        personService.registerPerson("Ana", 'f');
        personService.registerPerson("Carlos", 'm');
        personService.registerPerson("Maria", 'F');
        personService.registerPerson("João", 'M');

        List<Person> womenList = personService.filterOnlyWomen();

        womenList.forEach(person -> {
            Assertions.assertEquals('f', person.getGender(),
                    "Expected woman, but found: " + person.getName() + " with gender " + person.getGender());
        });
    }
}
