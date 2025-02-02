public class Person {

    private String name;
    private Character gender;

    public Person() {

    }

    public Person(String name, Character gender) {
        this.name = name;
        this.gender = gender;
    }

    @Override
    public String toString(){
        return "Person{name='" + name + "', gender=" + gender + "}";
    }



}
