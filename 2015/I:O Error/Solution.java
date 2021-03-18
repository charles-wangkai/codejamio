import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      sc.nextInt();
      String bytes = sc.next();

      System.out.println(String.format("Case #%d: %s", tc, solve(bytes)));
    }

    sc.close();
  }

  static String solve(String bytes) {
    return IntStream.range(0, bytes.length() / 8)
        .mapToObj(
            i ->
                String.valueOf(
                    (char)
                        Integer.parseInt(
                            bytes.substring(i * 8, i * 8 + 8).replace('O', '0').replace('I', '1'),
                            2)))
        .collect(Collectors.joining());
  }
}
