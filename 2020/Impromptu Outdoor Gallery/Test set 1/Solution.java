// https://en.wikipedia.org/wiki/Shoelace_formula

import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int N = sc.nextInt();
      int[] X = new int[N];
      int[] Y = new int[N];
      for (int i = 0; i < N; ++i) {
        X[i] = sc.nextInt();
        Y[i] = sc.nextInt();
      }

      System.out.println(String.format("Case #%d: %d", tc, solve(X, Y)));
    }

    sc.close();
  }

  static long solve(int[] X, int[] Y) {
    int N = X.length;

    long result = Long.MAX_VALUE;
    for (int i = 0; i < N; ++i) {
      for (int j = i + 1; j < N; ++j) {
        long negArea2 = Long.MIN_VALUE;
        long posArea2 = Long.MAX_VALUE;
        for (int k = 0; k < N; ++k) {
          if (k != i && k != j) {
            long area2 = computeDoubleArea(X, Y, new int[] {i, j, k});

            if (area2 < 0) {
              negArea2 = Math.max(negArea2, area2);
            } else {
              posArea2 = Math.min(posArea2, area2);
            }
          }
        }

        if (negArea2 != Long.MIN_VALUE && posArea2 != Long.MAX_VALUE) {
          result = Math.min(result, posArea2 - negArea2);
        }
      }
    }

    return result;
  }

  static long computeDoubleArea(int[] X, int[] Y, int[] indices) {
    long result = 0;
    for (int i = 0; i < indices.length; ++i) {
      int index1 = indices[i];
      int index2 = indices[(i + 1) % indices.length];

      result += (long) X[index1] * Y[index2] - (long) X[index2] * Y[index1];
    }

    return result;
  }
}
