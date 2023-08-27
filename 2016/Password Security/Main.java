import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
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
    if (!isPossible(P)) {
      return "IMPOSSIBLE";
    }

    List<Character> letters =
        IntStream.rangeClosed('A', 'Z').mapToObj(ch -> (char) ch).collect(Collectors.toList());
    while (true) {
      Collections.shuffle(letters);

      String permutation = letters.stream().map(String::valueOf).collect(Collectors.joining());
      if (Arrays.stream(P).allMatch(pi -> !permutation.contains(pi))) {
        return permutation;
      }
    }
  }

  static boolean isPossible(String[] P) {
    if (Arrays.stream(P).anyMatch(pi -> pi.length() == 1)) {
      return false;
    }

    Set<String> pSet = Arrays.stream(P).collect(Collectors.toSet());

    List<Character> firsts = new ArrayList<>();
    for (char c = 'A'; c <= 'Z'; ++c) {
      boolean first = true;
      for (char prev = 'A'; prev <= 'Z'; ++prev) {
        if (prev != c && !pSet.contains(String.format("%c%c", prev, c))) {
          first = false;

          break;
        }
      }

      if (first) {
        firsts.add(c);
      }
    }
    if (firsts.size() >= 2) {
      return false;
    }

    List<Character> lasts = new ArrayList<>();
    for (char c = 'A'; c <= 'Z'; ++c) {
      boolean last = true;
      for (char next = 'A'; next <= 'Z'; ++next) {
        if (next != c && !pSet.contains(String.format("%c%c", c, next))) {
          last = false;

          break;
        }
      }

      if (last) {
        lasts.add(c);
      }
    }

    return lasts.size() < 2
        && (firsts.isEmpty() || lasts.isEmpty() || !firsts.get(0).equals(lasts.get(0)));
  }
}
