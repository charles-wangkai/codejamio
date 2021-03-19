import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int N = sc.nextInt();
      String[] P = new String[N];
      for (int i = 0; i < P.length; ++i) {
        P[i] = sc.next();
      }

      System.out.println(String.format("Case #%d: %s", tc, solve(P)));
    }

    sc.close();
  }

  static String solve(String[] P) {
    if (P[0].length() == 1) {
      return "IMPOSSIBLE";
    }

    List<Character> letters =
        IntStream.rangeClosed('A', 'Z').mapToObj(ch -> (char) ch).collect(Collectors.toList());
    while (true) {
      Collections.shuffle(letters);

      String permutation = letters.stream().map(String::valueOf).collect(Collectors.joining());
      if (!permutation.contains(P[0])) {
        return permutation;
      }
    }
  }
}
