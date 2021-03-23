import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      String S = sc.next();

      System.out.println(String.format("Case #%d: %d", tc, solve(S)));
    }

    sc.close();
  }

  static int solve(String S) {
    int result = 0;
    int upperI = 0;
    int lowerI = 0;
    for (char ch : S.toCharArray()) {
      if (ch == 'I') {
        ++upperI;
      } else if (ch == 'i') {
        ++lowerI;
      } else if (ch == 'O') {
        if (upperI != 0) {
          --upperI;
          ++result;
        } else {
          --lowerI;
        }
      } else {
        if (lowerI != 0) {
          --lowerI;
        } else {
          --upperI;
        }
      }
    }

    return result;
  }
}
