import java.util.Scanner;

public class Solution {
  static final int[] R_OFFSETS = {-1, -1, 1, 1};
  static final int[] C_OFFSETS = {-1, 1, 1, -1};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int R = sc.nextInt();
      int C = sc.nextInt();
      char[][] map = new char[R][C];
      for (int r = 0; r < R; ++r) {
        String line = sc.next();
        for (int c = 0; c < C; ++c) {
          map[r][c] = line.charAt(c);
        }
      }

      System.out.println(String.format("Case #%d: %d", tc, solve(map)));
    }

    sc.close();
  }

  static int solve(char[][] map) {
    int R = map.length;
    int C = map[0].length;

    int result = 0;
    for (int r = 0; r < R; ++r) {
      for (int c = 0; c < C; ++c) {
        if (map[r][c] == 'X') {
          if (solve(sign(map, r, c)) == 0) {
            ++result;
          }
        }
      }
    }

    return result;
  }

  static char[][] sign(char[][] map, int row, int col) {
    int R = map.length;
    int C = map[0].length;

    char[][] result = new char[R][C];
    for (int r = 0; r < result.length; ++r) {
      result[r] = map[r].clone();
    }

    for (int i = 0; i < R_OFFSETS.length; ++i) {
      int r = row;
      int c = col;
      while (true) {
        r += R_OFFSETS[i];
        c += C_OFFSETS[i];
        if (!(r >= 0 && r < R && c >= 0 && c < C && result[r][c] != 'S')) {
          break;
        }

        result[r][c] = 'S';
      }
    }
    result[row][col] = 'S';

    return result;
  }
}