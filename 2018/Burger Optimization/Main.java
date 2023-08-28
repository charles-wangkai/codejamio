import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int K = sc.nextInt();
      int[] D = new int[K];
      for (int i = 0; i < D.length; ++i) {
        D[i] = sc.nextInt();
      }

      System.out.println(String.format("Case #%d: %d", tc, solve(D)));
    }

    sc.close();
  }

  static int solve(int[] D) {
    int K = D.length;

    int[] distances = IntStream.range(0, K).map(i -> Integer.min(i, K - 1 - i)).toArray();
    Arrays.sort(distances);
    Arrays.sort(D);

    return IntStream.concat(Arrays.stream(distances), Arrays.stream(D)).map(x -> x * x).sum()
        - 2 * IntStream.range(0, K).map(i -> distances[i] * D[i]).sum();
  }
}
