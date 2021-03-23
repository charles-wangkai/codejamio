import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int K = sc.nextInt();
      int[] M = new int[K + 1];
      for (int i = 0; i < M.length; ++i) {
        M[i] = sc.nextInt();
      }

      System.out.println(String.format("Case #%d: %d", tc, solve(M)));
    }

    sc.close();
  }

  static int solve(int[] M) {
    int result = 0;
    int beginIndex = 0;
    for (int i = 0; i < M.length; ++i) {
      if (isDesirable(M, beginIndex, i) && isDesirable(M, i, M.length - 1)) {
        ++result;
        beginIndex = i;
      }
    }

    return result;
  }

  static boolean isDesirable(int[] M, int beginIndex, int endIndex) {
    return !IntStream.range(beginIndex, endIndex).allMatch(i -> M[i] <= M[i + 1])
        && !IntStream.range(beginIndex, endIndex).allMatch(i -> M[i] >= M[i + 1]);
  }
}
