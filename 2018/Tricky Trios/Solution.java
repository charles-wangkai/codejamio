import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Solution {
  static Map<State, Double> cache = new HashMap<>();

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int N = sc.nextInt();

      System.out.println(String.format("Case #%d: %f", tc, solve(N)));
    }

    sc.close();
  }

  static double solve(int N) {
    return search(N, 0, 0, 0);
  }

  static double search(int rest, int known1, int known2, int known3) {
    if (rest == 0) {
      return 0;
    }
    if (known3 == 1) {
      return 1 + search(rest - 1, known1, known2, 0);
    }

    State state = new State(rest, known1, known2);
    if (!cache.containsKey(state)) {
      double result = 1;
      int known0 = rest - known1 - known2;
      int unknown = known0 * 3 + known1 * 2 + known2;

      if (known0 != 0) {
        double p1 = known0 * 3.0 / unknown;
        double p2Same = 2 / (unknown - 1.0);
        double p3Same = 1 / (unknown - 2.0);

        result += p1 * p2Same * p3Same * search(rest - 1, known1, known2, 0);

        if (known0 != 1) {
          result +=
              p1
                  * p2Same
                  * ((known0 - 1.0) * 3 / (unknown - 2))
                  * search(rest, known1 + 1, known2 + 1, 0);
        }
        if (known1 != 0) {
          result +=
              p1
                  * p2Same
                  * (known1 * 2.0 / (unknown - 2))
                  * search(rest, known1 - 1, known2 + 2, 0);
        }
        if (known2 != 0) {
          result += p1 * p2Same * (known2 / (unknown - 2.0)) * search(rest, known1, known2, 1);
        }

        if (known0 != 1) {
          result += p1 * ((known0 - 1.0) * 3 / (unknown - 1)) * search(rest, known1 + 2, known2, 0);
        }
        if (known1 != 0) {
          result += p1 * (known1 * 2.0 / (unknown - 1)) * search(rest, known1, known2 + 1, 0);
        }
        if (known2 != 0) {
          result += p1 * (known2 / (unknown - 1.0)) * search(rest, known1 + 1, known2 - 1, 1);
        }
      }
      if (known1 != 0) {
        double p1 = known1 * 2.0 / unknown;
        double p2Same = 1 / (unknown - 1.0);

        result += p1 * p2Same * search(rest - 1, known1 - 1, known2, 0);

        if (known0 != 0) {
          result += p1 * (known0 * 3.0 / (unknown - 1)) * search(rest, known1, known2 + 1, 0);
        }
        if (known1 != 1) {
          result +=
              p1 * ((known1 - 1.0) * 2 / (unknown - 1)) * search(rest, known1 - 2, known2 + 2, 0);
        }
        if (known2 != 0) {
          result += p1 * (known2 / (unknown - 1.0)) * search(rest, known1 - 1, known2, 1);
        }
      }
      if (known2 != 0) {
        double p1 = (double) known2 / unknown;

        result += p1 * search(rest - 1, known1, known2 - 1, 0);
      }

      cache.put(state, result);
    }

    return cache.get(state);
  }
}

class State {
  int rest;
  int known1;
  int known2;

  State(int rest, int known1, int known2) {
    this.rest = rest;
    this.known1 = known1;
    this.known2 = known2;
  }

  @Override
  public int hashCode() {
    return Objects.hash(rest, known1, known2);
  }

  @Override
  public boolean equals(Object obj) {
    State other = (State) obj;

    return rest == other.rest && known1 == other.known1 && known2 == other.known2;
  }
}
