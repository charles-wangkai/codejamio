import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int R = sc.nextInt();
      int C = sc.nextInt();
      int K = sc.nextInt();

      System.out.println(String.format("Case #%d: %s", tc, solve(R, C, K)));
    }

    sc.close();
  }

  static String solve(int R, int C, int K) {
    int rest = R * C - K;

    if (rest == 1) {
      return "IMPOSSIBLE";
    }

    char[][] grid = new char[R][C];
    for (int r = 0; r < R; ++r) {
      for (int c = 0; c < C; ++c) {
        if (rest == 0) {
          grid[r][c] = 'S';
        } else {
          if (r == 0 && c == 0) {
            grid[r][c] = (C == 1) ? 'S' : 'E';
          } else if (r == 0 && c == 1) {
            grid[r][c] = 'W';
          } else {
            grid[r][c] = (r == 0) ? 'W' : 'N';
          }

          --rest;
        }
      }
    }

    return String.format(
        "POSSIBLE\n%s", Arrays.stream(grid).map(String::new).collect(Collectors.joining("\n")));
  }
}
