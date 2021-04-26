import java.util.Arrays;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      String B = sc.next();

      System.out.println(String.format("Case #%d: %s", tc, solve(B)));
    }

    sc.close();
  }

  static String solve(String B) {
    int[][][] maxScores = new int[B.length()][B.length()][2];
    for (int i = 0; i < B.length(); ++i) {
      for (int j = 0; j < B.length(); ++j) {
        Arrays.fill(maxScores[i][j], Integer.MIN_VALUE);
      }
    }

    for (int length = 1; length <= B.length(); ++length) {
      for (int beginIndex = 0; beginIndex + length <= B.length(); ++beginIndex) {
        int endIndex = beginIndex + length - 1;
        if (length == 1) {
          maxScores[beginIndex][endIndex][0] = (B.charAt(beginIndex) == 'I') ? -2 : 1;
          maxScores[beginIndex][endIndex][1] = (B.charAt(beginIndex) == 'I') ? 1 : -2;
        } else {
          if (B.charAt(beginIndex) == 'I') {
            maxScores[beginIndex][endIndex][0] =
                Math.max(maxScores[beginIndex][endIndex][0], -1 - length);
            maxScores[beginIndex][endIndex][1] =
                Math.max(
                    maxScores[beginIndex][endIndex][1], -maxScores[beginIndex + 1][endIndex][0]);
          } else {
            maxScores[beginIndex][endIndex][0] =
                Math.max(
                    maxScores[beginIndex][endIndex][0], -maxScores[beginIndex + 1][endIndex][1]);
            maxScores[beginIndex][endIndex][1] =
                Math.max(maxScores[beginIndex][endIndex][1], -1 - length);
          }

          if (B.charAt(endIndex) == 'I') {
            maxScores[beginIndex][endIndex][0] =
                Math.max(maxScores[beginIndex][endIndex][0], -1 - length);
            maxScores[beginIndex][endIndex][1] =
                Math.max(
                    maxScores[beginIndex][endIndex][1], -maxScores[beginIndex][endIndex - 1][0]);
          } else {
            maxScores[beginIndex][endIndex][0] =
                Math.max(
                    maxScores[beginIndex][endIndex][0], -maxScores[beginIndex][endIndex - 1][1]);
            maxScores[beginIndex][endIndex][1] =
                Math.max(maxScores[beginIndex][endIndex][1], -1 - length);
          }
        }
      }
    }

    return String.format(
        "%c %d",
        (maxScores[0][B.length() - 1][1] > 0) ? 'I' : 'O',
        Math.abs(maxScores[0][B.length() - 1][1]));
  }
}
