import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int A = sc.nextInt();
      int B = sc.nextInt();
      int alpha = sc.nextInt();
      int beta = sc.nextInt();
      long Y = sc.nextLong();

      System.out.println(String.format("Case #%d: %s", tc, solve(A, B, alpha, beta, Y)));
    }

    sc.close();
  }

  static String solve(int A, int B, int alpha, int beta, long Y) {
    Map<State, Integer> stateToIndex = new HashMap<>();
    stateToIndex.put(new State(A, B), 0);

    long restYear = Y - 1;
    while (restYear >= 0) {
      int offspring = Math.min(A, B) * 2 / 100;
      int aBorn = offspring * alpha / 100;
      int bBorn = offspring * beta / 100;
      int rest = offspring - aBorn - bBorn;
      aBorn += rest / 2;
      bBorn += rest - rest / 2;

      A += aBorn - A / 100;
      B += bBorn - B / 100;

      State state = new State(A, B);
      if (stateToIndex.containsKey(state)) {
        restYear %= stateToIndex.size() - stateToIndex.get(state);
      } else {
        stateToIndex.put(state, stateToIndex.size());
      }

      --restYear;
    }

    return String.format("%d %d", A, B);
  }
}

class State {
  int a;
  int b;

  State(int a, int b) {
    this.a = a;
    this.b = b;
  }

  @Override
  public int hashCode() {
    return Objects.hash(a, b);
  }

  @Override
  public boolean equals(Object obj) {
    State other = (State) obj;

    return a == other.a && b == other.b;
  }
}
