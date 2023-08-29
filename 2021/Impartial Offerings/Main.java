import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int N = sc.nextInt();
      int[] S = new int[N];
      for (int i = 0; i < S.length; ++i) {
        S[i] = sc.nextInt();
      }

      System.out.println(String.format("Case #%d: %d", tc, solve(S)));
    }

    sc.close();
  }

  static int solve(int[] S) {
    SortedMap<Integer, Integer> sizeToCount = new TreeMap<>();
    for (int size : S) {
      sizeToCount.put(size, sizeToCount.getOrDefault(size, 0) + 1);
    }

    int result = 0;
    int treat = 1;
    for (int count : sizeToCount.values()) {
      result += treat * count;
      ++treat;
    }

    return result;
  }
}
