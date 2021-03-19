import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int D = sc.nextInt();
      int K = sc.nextInt();
      int N = sc.nextInt();

      System.out.println(String.format("Case #%d: %s", tc, solve(D, K, N)));
    }

    sc.close();
  }

  static String solve(int D, int K, int N) {
    int y = (K + ((K % 2 == 0) ? -2 : 2) * N % D + D) % D + 1;
    int z = (y - 3 + D) % D + 1;

    return String.format("%d %d", y, z);
  }
}
