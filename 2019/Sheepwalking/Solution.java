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
      int X = sc.nextInt();
      int Y = sc.nextInt();

      System.out.println(String.format("Case #%d: %f", tc, solve(X, Y)));
    }

    sc.close();
  }

  static double solve(int X, int Y) {
    if (X < 0) {
      return solve(-X, Y);
    }
    if (Y < 0) {
      return solve(X, -Y);
    }
    if (X > Y) {
      return solve(Y, X);
    }
    if (X == 0 && Y == 0) {
      return 0;
    }

    State state = new State(X, Y);
    if (!cache.containsKey(state)) {
      double result;
      if (X == 0) {
        if (Y == 1) {
          result = 3;
        } else {
          result = (1.5 + solve(1, Y - 1) / 4 + solve(0, Y - 1) / 2) / 0.75;
        }
      } else {
        result = 1 + solve(X - 1, Y) / 2 + solve(X, Y - 1) / 2;
      }

      cache.put(state, result);
    }

    return cache.get(state);
  }
}

class State {
  int x;
  int y;

  State(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public boolean equals(Object obj) {
    State other = (State) obj;

    return x == other.x && y == other.y;
  }
}
