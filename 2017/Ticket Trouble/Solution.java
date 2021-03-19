import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int F = sc.nextInt();
      int S = sc.nextInt();
      int[] A = new int[F];
      int[] B = new int[F];
      for (int i = 0; i < F; ++i) {
        A[i] = sc.nextInt();
        B[i] = sc.nextInt();
      }

      System.out.println(String.format("Case #%d: %d", tc, solve(A, B, S)));
    }

    sc.close();
  }

  static int solve(int[] A, int[] B, int S) {
    return IntStream.rangeClosed(1, S)
        .map(
            r ->
                (int)
                    IntStream.range(0, A.length)
                        .filter(i -> A[i] == r || B[i] == r)
                        .map(i -> (A[i] == r) ? B[i] : A[i])
                        .distinct()
                        .count())
        .max()
        .getAsInt();
  }
}
