import java.util.*;
import java.util.stream.Collectors;

/**
 * Program to process and sort names with genders, grouped by gender.
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

        Map<String, List<String>> groupedNames = groupNamesByGender(namesAndGenders);

        printGroupedNames(groupedNames);
    }

    private static Map<String, List<String>> groupNamesByGender(String input) {
        Map<String, List<String>> genderGroups = new HashMap<>();

        Arrays.stream(input.split(","))
                .map(String::trim)
                .forEach(name -> {
                    if (name.contains("- M") || name.contains("-M")) {
                        genderGroups.computeIfAbsent("Male", k -> new ArrayList<>())
                                .add(name.replaceAll("- M|-M", "").trim());
                    } else if (name.contains("- F") || name.contains("-F")) {
                        genderGroups.computeIfAbsent("Female", k -> new ArrayList<>())
                                .add(name.replaceAll("- F|-F", "").trim());
                    }
                });

        genderGroups.forEach((gender, names) -> names.sort(String::compareTo));
        return genderGroups;
    }

    private static void printGroupedNames(Map<String, List<String>> groupedNames) {
        groupedNames.forEach((gender, names) -> {
            System.out.printf("\n%s\n=================\n", gender);
            names.forEach(System.out::println);
        });
    }
}
