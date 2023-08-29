import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
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
      System.out.println(randomWord());
      System.out.flush();

      List<String> robotWords = new ArrayList<>();
      for (int i = 0; i < 49; ++i) {
        String robotWord = sc.next();
        if (robotWord.equals("-1")) {
          return;
        }
        robotWords.add(robotWord);

        System.out.println(robotWord);
        System.out.flush();
      }

      Pair extremePair = findExtremePair(robotWords);

      for (int i = 0; i < 51; ++i) {
        String robotWord = sc.next();
        if (robotWord.equals("-1")) {
          return;
        }

        System.out.println((i % 2 == 0) ? extremePair.s1 : randomWord());
        System.out.flush();
      }
    }
  }

  static Pair findExtremePair(List<String> robotWords) {
    Pair[] pairs =
        IntStream.range(0, robotWords.size() - 1)
            .mapToObj(i -> new Pair(robotWords.get(i), robotWords.get(i + 1)))
            .toArray(Pair[]::new);

    Map<Pair, Integer> pairToCount = new HashMap<>();
    for (Pair pair : pairs) {
      pairToCount.put(pair, pairToCount.getOrDefault(pair, 0) + 1);
    }

    int maxCount = pairToCount.values().stream().mapToInt(x -> x).max().getAsInt();

    Map<Pair, List<Integer>> candidatePairToDistances =
        pairToCount.keySet().stream()
            .filter(pair -> pairToCount.get(pair) == maxCount)
            .collect(Collectors.toMap(pair -> pair, pair -> new ArrayList<>()));

    int prevIndex = -1;
    for (int i = 0; i < pairs.length; ++i) {
      if (candidatePairToDistances.containsKey(pairs[i])) {
        if (prevIndex != -1) {
          candidatePairToDistances.get(pairs[prevIndex]).add(i - prevIndex);
        }

        prevIndex = i;
      }
    }

    return candidatePairToDistances.keySet().stream()
        .max(
            Comparator.comparing(
                pair ->
                    candidatePairToDistances.get(pair).stream()
                        .mapToInt(x -> x)
                        .average()
                        .getAsDouble()))
        .get();
  }

  static String randomWord() {
    return IntStream.range(0, 5)
        .mapToObj(i -> String.valueOf((char) (RANDOM.nextInt(10) + 'A')))
        .collect(Collectors.joining());
  }
}

class Pair {
  String s1;
  String s2;

  Pair(String s1, String s2) {
    this.s1 = s1;
    this.s2 = s2;
  }

  @Override
  public int hashCode() {
    return Objects.hash(s1, s2);
  }

  @Override
  public boolean equals(Object obj) {
    Pair other = (Pair) obj;

    return s1.equals(other.s1) && s2.equals(other.s2);
  }
}
