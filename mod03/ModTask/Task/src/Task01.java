import java.math.BigInteger;

public class Task01 {
    public static long calculateFactorialBigInteger(int number) {
        if (number == 0 || number == 1) {
            return 1;
        }
        return number * calculateFactorialBigInteger(number - 1);
    }

    public static BigInteger calculateFactorialBigIntegerV2(int number) {
        if (number == 0 || number == 1) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(number).multiply(calculateFactorialBigIntegerV2(number - 1));
    }

    public static void main(String[] args) {
        int n = 101; // exemplo
        long fatorial = calculateFactorialBigInteger(n);
        System.out.println(n + "! = " + fatorial);

        int n2 = 101;
        BigInteger fatorial2 = calculateFactorialBigIntegerV2(n2);
        System.out.println(n2 + "! = " + fatorial2);

    }
}