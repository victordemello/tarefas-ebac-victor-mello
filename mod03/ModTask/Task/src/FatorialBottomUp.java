import java.math.BigInteger;

public class FatorialBottomUp {

    public static BigInteger factorial(int n) {
        BigInteger[] dp = new BigInteger[n + 1];
        dp[0] = BigInteger.ONE;
        dp[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            dp[i] = BigInteger.valueOf(i).multiply(dp[i - 1]);
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 101;
        System.out.println(n + "! = " + factorial(n));
    }
}