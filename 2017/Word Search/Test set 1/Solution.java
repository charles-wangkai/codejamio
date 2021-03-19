import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
  static final int SIZE = 50;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int D = sc.nextInt();
      int N = sc.nextInt();

      System.out.println(String.format("Case #%d:\n%s", tc, solve(D, N)));
    }

    sc.close();
  }

  static String solve(int D, int N) {
    char[][] grid = new char[SIZE][SIZE];
    for (int r = 0; r < SIZE; ++r) {
      for (int c = 0; c < SIZE; ++c) {
        int rMod = r % 5;
        int cMod = c % 5;

        if (rMod == 2 && cMod == 2) {
          grid[r][c] = 'I';
        } else if (N != 0
            && (rMod == 0 || rMod == 4 || cMod == 0 || cMod == 4)
            && (rMod + cMod) % 2 == 0) {
          grid[r][c] = 'O';
          --N;
        } else {
          grid[r][c] = '/';
        }
      }
    }

    return Arrays.stream(grid).map(String::new).collect(Collectors.joining("\n"));
  }
}
