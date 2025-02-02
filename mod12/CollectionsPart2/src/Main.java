import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        List<Person> people = new ArrayList<>();
        Map<Character, List<Person>> groupOfPeople = new HashMap<>();
        Character choice = null;

        System.out.println("=======================");
        System.out.println("Online Registration");
        System.out.println("=======================");
        System.out.println(" ");

        do {
            System.out.println("Name: ");
            String name = sc.nextLine();

            System.out.println("Gender: (m/f)");
            Character gender = sc.nextLine().charAt(0);

            while (validateGender(gender)){
                System.out.println("Please enter a valid gender:");
                gender = sc.nextLine()
                        .trim()
                        .toUpperCase()
                        .charAt(0);
            }

            groupOfPeople.computeIfAbsent(gender, k -> new ArrayList<>())
                    .add(new Person(name, gender));

            System.out.println("Do you want to register someone else? (y/n)");
            choice = sc.nextLine()
                    .trim()
                    .toUpperCase()
                    .charAt(0);

            while (!validateChoice(choice)){
                System.out.println("Enter a valid option y/n");
                choice = sc.nextLine()
                        .trim()
                        .toLowerCase()
                        .charAt(0);
            }

        }while (choice == 'Y');

        sc.close();

        System.out.println("================================");
        System.out.println("List of registered people by group:");
        System.out.println("================================");
        System.out.println(" ");

        printGroups(groupOfPeople);

    }

    private static boolean validateGender(Character gender){
        return gender.equals('M') || gender.equals('F');
    }

    private static boolean validateChoice(Character choice){
        return choice.equals('Y') || choice.equals('N');
    }

    private static void printGroups(Map<Character, List<Person>> groupOfPeople){
        for (Map.Entry<Character, List<Person>> entry : groupOfPeople.entrySet()) {
            System.out.println("Grupo: " + entry.getKey());
            for (Person person : entry.getValue()) {
                System.out.println(" - " + person.toString());
            }
        }
    }
}