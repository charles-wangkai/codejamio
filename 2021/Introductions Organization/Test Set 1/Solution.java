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
    return IntStream.range(0, A.length)
        .mapToObj(i -> String.valueOf(computeMinTime(M, N, C, A[i], B[i])))
        .collect(Collectors.joining(" "));
  }

  static int computeMinTime(int M, int N, char[][] C, int a, int b) {
    int minTime = search(M, N, a, b, C, 0);

    return (minTime == Integer.MAX_VALUE) ? -1 : minTime;
  }

  static int search(int M, int N, int a, int b, char[][] C, int time) {
    if (C[a][b] == 'Y') {
      return time;
    }

    int result = Integer.MAX_VALUE;
    for (int k = 0; k < M; ++k) {
      for (int i = 0; i < M + N; ++i) {
        for (int j = i + 1; j < M + N; ++j) {
          if (C[i][j] == 'N' && C[i][k] == 'Y' && C[j][k] == 'Y') {
            C[i][j] = 'Y';
            C[j][i] = 'Y';

            result = Math.min(result, search(M, N, a, b, C, time + 1));

            C[i][j] = 'N';
            C[j][i] = 'N';
          }
        }
      }
    }

    return result;
  }
}
