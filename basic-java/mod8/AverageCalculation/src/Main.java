import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Average Calculation");

        Scanner sc = new Scanner(System.in);
        List<Double> gradesList = new ArrayList<>();

        System.out.println("Enter grade 1");
        gradesList.add(sc.nextDouble());
        System.out.println("Enter grade 2");
        gradesList.add(sc.nextDouble());
        System.out.println("Enter grade 3");
        gradesList.add(sc.nextDouble());
        System.out.println("Enter grade 4");
        gradesList.add(sc.nextDouble());

        sc.close();

        double average = gradesList.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        System.out.println("=====================");
        System.out.println("NOTAS INFORMADAS");
        System.out.println("=====================");

        System.out.println(gradesList);
        System.out.println("Average = " + average);
    }
}