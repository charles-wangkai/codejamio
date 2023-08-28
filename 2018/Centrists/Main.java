import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      sc.nextInt();
      String[] N = new String[3];
      for (int i = 0; i < N.length; ++i) {
        N[i] = sc.next();
      }

      System.out.println(String.format("Case #%d: %s", tc, solve(N)));
    }

    sc.close();
  }

  static String solve(String[] N) {
    boolean[] possibles = new boolean[N.length];
    for (int i = 0; i < N.length; ++i) {
      for (int j = 0; j < N.length; ++j) {
        if (j != i) {
          for (int k = 0; k < N.length; ++k) {
            if (k != i && k != j && check(N[i], N[j], N[k])) {
              possibles[j] = true;
            }
          }
        }
      }
    }

    return IntStream.range(0, possibles.length)
        .mapToObj(i -> possibles[i] ? "YES" : "NO")
        .collect(Collectors.joining(" "));
  }

  static boolean check(String name1, String name2, String name3) {
    Relation r1 = findRelation(name1, name2);
    Relation r2 = findRelation(name2, name3);

    return r1.lower != r2.upper || r1.upper != r2.lower;
  }

  static Relation findRelation(String s1, String s2) {
    for (int i = 0; ; ++i) {
      char ch1 = s1.charAt(i);
      char ch2 = s2.charAt(i);
      if (ch1 != ch2) {
        return new Relation(ch1, ch2);
      }
    }
  }
}

class Relation {
  char lower;
  char upper;

  Relation(char lower, char upper) {
    this.lower = lower;
    this.upper = upper;
  }
}
