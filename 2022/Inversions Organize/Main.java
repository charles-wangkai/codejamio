import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int N = sc.nextInt();
      char[][] matrix = new char[2 * N][2 * N];
      for (int r = 0; r < 2 * N; ++r) {
        String line = sc.next();
        for (int c = 0; c < 2 * N; ++c) {
          matrix[r][c] = line.charAt(c);
        }
      }

      System.out.println(String.format("Case #%d: %d", tc, solve(matrix)));
    }

    sc.close();
  }

  static int solve(char[][] matrix) {
    int N = matrix.length / 2;

    return Math.abs(
            computeINum(matrix, 0, 0, N - 1, N - 1)
                - computeINum(matrix, N, N, 2 * N - 1, 2 * N - 1))
        + Math.abs(
            computeINum(matrix, 0, N, N - 1, 2 * N - 1)
                - computeINum(matrix, N, 0, 2 * N - 1, N - 1));
  }

  static int computeINum(char[][] matrix, int minR, int minC, int maxR, int maxC) {
    int result = 0;
    for (int r = minR; r <= maxR; ++r) {
      for (int c = minC; c <= maxC; ++c) {
        if (matrix[r][c] == 'I') {
          ++result;
        }
      }
    }

    return result;
  }
}
