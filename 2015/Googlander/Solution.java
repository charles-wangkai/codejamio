import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int R = sc.nextInt();
      int C = sc.nextInt();

      System.out.println(String.format("Case #%d: %d", tc, solve(R, C)));
    }

    sc.close();
  }

  static long solve(int R, int C) {
    long[][] cache = new long[Math.max(R, C) + 1][Math.max(R, C) + 1];

    return search(cache, R, C);
  }

  static long search(long[][] cache, int row, int col) {
    if (cache[row][col] == 0) {
      if (row == 1 || col == 1) {
        cache[row][col] = 1;
      } else {
        for (int i = 0; i < row; ++i) {
          cache[row][col] += search(cache, col - 1, i + 1);
        }
      }
    }

    return cache[row][col];
  }
}
