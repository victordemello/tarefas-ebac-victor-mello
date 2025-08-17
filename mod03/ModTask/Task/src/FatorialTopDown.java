import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class FatorialTopDown {
    private static Map<Integer, BigInteger> memo = new HashMap<>();

    public static BigInteger factorial(int n) {
        if (n == 0 || n == 1) return BigInteger.ONE;

        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        BigInteger result = BigInteger.valueOf(n).multiply(factorial(n - 1));
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        int n = 7;
        System.out.println(n + "! = " + factorial(n));
    }
}
