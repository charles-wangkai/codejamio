import java.util.Scanner;

public class Main {
  static final int MODULUS = 1_000_000_007;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int C = sc.nextInt();
      int V = sc.nextInt();
      int L = sc.nextInt();

      System.out.println(String.format("Case #%d: %d", tc, solve(C, V, L)));
    }

    sc.close();
  }

  static int solve(int C, int V, int L) {
    int[] dp = new int[L + 1];
    dp[0] = 1;
    for (int i = 1; i < dp.length; ++i) {
      dp[i] = addMod(dp[i], multiplyMod(dp[i - 1], V));
      if (i != 1) {
        dp[i] = addMod(dp[i], multiplyMod(dp[i - 2], C * V));
      }
    }

    return dp[L];
  }

  static int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  static int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }
}
