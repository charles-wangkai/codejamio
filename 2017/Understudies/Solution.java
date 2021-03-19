import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int N = sc.nextInt();
      double[] P = new double[2 * N];
      for (int i = 0; i < P.length; ++i) {
        P[i] = sc.nextDouble();
      }

      System.out.println(String.format("Case #%d: %f", tc, solve(P)));
    }

    sc.close();
  }

  static double solve(double[] P) {
    Arrays.sort(P);

    return IntStream.range(0, P.length / 2)
        .mapToDouble(i -> 1 - P[i] * P[P.length - 1 - i])
        .reduce((x, y) -> x * y)
        .getAsDouble();
  }
}
