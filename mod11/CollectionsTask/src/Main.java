import java.util.*;
import java.util.stream.Collectors;

/**
 * Program to process and sort names with genders.
 * Author: Victor Mello
 */
public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            processNames(sc);
            processNamesAndGenders(sc);
        }
    }

    private static void processNames(Scanner sc) {
        System.out.println("Enter names separated by commas:");
        String names = sc.nextLine();

        List<String> sortedNames = Arrays.stream(names.split(","))
                .map(String::trim)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("\nList of names in alphabetical order:");
        sortedNames.forEach(System.out::println);
    }

    private static void processNamesAndGenders(Scanner sc) {
        System.out.println("\n===================================================================================================");
        System.out.println("Enter the name and gender separating with - and , For example: Maria - F, John - M, Arthur - M");
        String namesAndGenders = sc.nextLine().toUpperCase();

        List<String> men = filterAndSortByGender(namesAndGenders, "M");
        List<String> women = filterAndSortByGender(namesAndGenders, "F");

        printList("Men", men);
        printList("Women", women);
    }

    private static List<String> filterAndSortByGender(String input, String gender) {
        return Arrays.stream(input.split(","))
                .filter(name -> name.contains("- " + gender) || name.contains("-" + gender))
                .map(String::trim)
                .sorted()
                .collect(Collectors.toList());
    }

    private static void printList(String title, List<String> names) {
        System.out.printf("\n%s\n=================\n", title);
        names.forEach(System.out::println);
    }
}
