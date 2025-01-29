import enumerator.ApprovalStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================");
        System.out.println("Student grade assessment system");
        System.out.println("==================================");

        List<String> ex = new Stack<>();

        Scanner sc = new Scanner(System.in);
        List<Double> studentGrades = new ArrayList<>();
        double gradeAverage;
        ApprovalStatus approvalStatus;

        for(int index = 0; index < 4; index++){
            System.out.printf("Enter Grade %d:\n", index + 1);
            studentGrades.add(sc.nextDouble());
        }

        sc.close();

        int count = 1;

        for(double grade: studentGrades){
            System.out.println("------------");
            System.out.printf("Grade %d: %.2f \n", count, grade);
            count++;
        }

        gradeAverage = studentGrades.stream().mapToDouble(grade -> grade).average().orElse(0.0);

        System.out.println(" ");

        if (gradeAverage >= 7){
            approvalStatus = ApprovalStatus.APPROVED;
        }
        else if(gradeAverage >= 5){
            approvalStatus = ApprovalStatus.RECOVERY;

        }
        else {
            approvalStatus = ApprovalStatus.FAILED;
        }

        System.out.println("Average: " + gradeAverage);
        System.out.println("Status: " + approvalStatus);

    }
}