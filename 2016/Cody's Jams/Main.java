import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int N = sc.nextInt();
      int[] P = new int[2 * N];
      for (int i = 0; i < P.length; ++i) {
        P[i] = sc.nextInt();
      }

      System.out.println(String.format("Case #%d: %s", tc, solve(P)));
    }

    sc.close();
  }

  static String solve(int[] P) {
    List<Integer> rest = Arrays.stream(P).boxed().collect(Collectors.toList());
    List<Integer> result = new ArrayList<>();
    while (!rest.isEmpty()) {
      int sale = rest.remove(0);
      result.add(sale);
      rest.remove(Integer.valueOf(sale / 3 * 4));
    }

    return result.stream().map(String::valueOf).collect(Collectors.joining(" "));
  }
}
