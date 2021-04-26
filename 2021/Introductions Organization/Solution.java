import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int M = sc.nextInt();
      int N = sc.nextInt();
      int P = sc.nextInt();
      char[][] C = new char[M + N][M + N];
      for (int i = 0; i < M + N; ++i) {
        String line = sc.next();
        for (int j = 0; j < M + N; ++j) {
          C[i][j] = line.charAt(j);
        }
      }
      int[] A = new int[P];
      int[] B = new int[P];
      for (int i = 0; i < P; ++i) {
        A[i] = sc.nextInt() - 1;
        B[i] = sc.nextInt() - 1;
      }

      System.out.println(String.format("Case #%d: %s", tc, solve(M, N, C, A, B)));
    }

    sc.close();
  }

  static String solve(int M, int N, char[][] C, int[] A, int[] B) {
    int[][] distances = new int[M + N][M + N];
    for (int i = 0; i < distances.length; ++i) {
      for (int j = 0; j < distances[i].length; ++j) {
        distances[i][j] = (i == j) ? 0 : ((C[i][j] == 'Y') ? 1 : Integer.MAX_VALUE);
      }
    }

    for (int k = 0; k < M; ++k) {
      for (int i = 0; i < M + N; ++i) {
        for (int j = 0; j < M + N; ++j) {
          if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE) {
            distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
          }
        }
      }
    }

    return IntStream.range(0, A.length)
        .mapToObj(
            i ->
                String.valueOf(
                    (distances[A[i]][B[i]] == Integer.MAX_VALUE) ? -1 : f(distances[A[i]][B[i]])))
        .collect(Collectors.joining(" "));
  }

  static int f(int x) {
    if (x <= 1) {
      return 0;
    }

    return 1 + f(x - (x + 1) / 3);
  }
}
