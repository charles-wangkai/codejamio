import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  static final Random RANDOM = new Random();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    solve(sc);

    sc.close();
  }

  static void solve(Scanner sc) {
    int T = sc.nextInt();
    sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      System.out.println(generate());
      System.out.flush();

      List<String> robotWords = new ArrayList<>();
      for (int i = 0; i < 100; ++i) {
        String robotWord = sc.next();
        if (robotWord.equals("-1")) {
          return;
        }

        System.out.println(
            robotWords.isEmpty() ? generate() : robotWords.get(RANDOM.nextInt(robotWords.size())));
        System.out.flush();

        robotWords.add(robotWord);
      }
    }
  }

  static String generate() {
    return IntStream.range(0, 5)
        .mapToObj(i -> String.valueOf((char) (RANDOM.nextInt(10) + 'A')))
        .collect(Collectors.joining());
  }
}
