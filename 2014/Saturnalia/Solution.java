import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    sc.nextLine();
    for (int tc = 1; tc <= T; ++tc) {
      String message = sc.nextLine();

      System.out.println(String.format("Case #%d:\n%s", tc, solve(message)));
    }

    sc.close();
  }

  static String solve(String message) {
    char[][] cells = new char[3][message.length() + 4];
    int row = cells.length;
    int col = cells[0].length;
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        if ((r == 0 || r == row - 1) && (c == 0 || c == col - 1)) {
          cells[r][c] = '+';
        } else if (r == 0 || r == row - 1) {
          cells[r][c] = '-';
        } else if (c == 0 || c == col - 1) {
          cells[r][c] = '|';
        } else if (c >= 2 && c <= col - 3) {
          cells[r][c] = message.charAt(c - 2);
        } else {
          cells[r][c] = ' ';
        }
      }
    }

    return Arrays.stream(cells).map(String::new).collect(Collectors.joining("\n"));
  }
}
