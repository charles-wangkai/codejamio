import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int N = sc.nextInt();
      int K = sc.nextInt();

      System.out.println(String.format("Case #%d: %d", tc, solve(N, K)));
    }

    sc.close();
  }

  static long solve(int N, int K) {
    int[] sizes = IntStream.range(0, K).map(i -> N / K + ((i < N % K) ? 1 : 0)).toArray();

    long result = 1;
    int rest = N;
    for (int size : sizes) {
      result *= C(rest, size);
      rest -= size;
    }
    result /= P((int) Arrays.stream(sizes).filter(x -> x == sizes[0]).count());
    result /= P((int) Arrays.stream(sizes).filter(x -> x == sizes[0] - 1).count());

    for (int size : sizes) {
      if (size >= 3) {
        result *= P(size - 1) / 2;
      }
    }

    return result;
  }

  static long P(int n) {
    return IntStream.rangeClosed(1, n).asLongStream().reduce(1, (x, y) -> x * y);
  }

  static int C(int n, int r) {
    int result = 1;
    for (int i = 0; i < r; ++i) {
      result = result * (n - i) / (i + 1);
    }

    return result;
  }
}
