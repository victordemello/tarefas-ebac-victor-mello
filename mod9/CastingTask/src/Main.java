import java.util.Scanner;

/**
 * @author victor.mello
 */
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite um valor númerico (ex: 7)");
        double number = sc.nextDouble();

        System.out.println("Valor em tipo primitivo digitado (double): " + number);
        Double numberObject = Double.valueOf(number);

        System.out.println("Valor convertido para classe Wrapper equivalente (Double): " + numberObject);
    }
}