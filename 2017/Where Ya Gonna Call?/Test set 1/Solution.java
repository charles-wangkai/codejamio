import java.util.Scanner;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int B = sc.nextInt();
      int[][] D = new int[B][];
      for (int i = 0; i < D.length; ++i) {
        D[i] = new int[i];
        for (int j = 0; j < D[i].length; ++j) {
          D[i][j] = sc.nextInt();
        }
      }

      System.out.println(String.format("Case #%d: %f", tc, solve(D)));
    }

    sc.close();
  }

  static double solve(int[][] D) {
    int B = D.length;

    int[][] distances = new int[B][B];
    for (int i = 0; i < B; ++i) {
      for (int j = 0; j < B; ++j) {
        if (i < j) {
          distances[i][j] = D[j][i];
        } else if (i > j) {
          distances[i][j] = D[i][j];
        } else {
          distances[i][j] = 0;
        }

        if (distances[i][j] == -1) {
          distances[i][j] = Integer.MAX_VALUE;
        }
      }
    }

    for (int k = 0; k < B; ++k) {
      for (int i = 0; i < B; ++i) {
        for (int j = 0; j < B; ++j) {
          if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE) {
            distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
          }
        }
      }
    }

    double result = Double.MAX_VALUE;
    for (int i = 0; i < B; ++i) {
      for (int j = 0; j < i; ++j) {
        if (D[i][j] != -1) {
          for (int k = 0; k <= 4; ++k) {
            int i_ = i;
            int j_ = j;
            int k_ = k;
            result =
                Math.min(
                    result,
                    IntStream.range(0, B)
                        .mapToDouble(
                            p ->
                                Math.min(
                                    distances[p][i_] + D[i_][j_] / 4.0 * k_,
                                    distances[p][j_] + D[i_][j_] / 4.0 * (4 - k_)))
                        .max()
                        .getAsDouble());
          }
        }
      }
    }

    return result;
  }
}
