import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int N = sc.nextInt();
      int[] L = new int[N];
      for (int i = 0; i < L.length; ++i) {
        L[i] = sc.nextInt();
      }

      System.out.println(String.format("Case #%d: %s", tc, solve(L)));
    }

    sc.close();
  }

  static String solve(int[] L) {
    StringBuilder result = new StringBuilder("A");
    for (int i = 0; i < L.length; i += 2) {
      int upLength = L[i];
      int downLength = (i + 1 == L.length) ? 0 : L[i + 1];

      if (upLength >= downLength) {
        for (int j = 0; j < upLength; ++j) {
          result.append((char) ('B' + j));
        }
        for (int j = 0; j < downLength; ++j) {
          result.append((char) ('A' + downLength - 1 - j));
        }
      } else {
        for (int j = 0; j < upLength - 1; ++j) {
          result.append((char) ('B' + j));
        }
        for (int j = 0; j < downLength + 1; ++j) {
          result.append((char) ('A' + downLength - j));
        }
      }
    }

    return result.toString();
  }
}
