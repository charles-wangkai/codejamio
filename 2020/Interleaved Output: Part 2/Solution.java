import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
  static final String[] EVENTS = {"IO", "Io", "iO", "io"};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      String S = sc.next();

      System.out.println(String.format("Case #%d: %d", tc, solve(S)));
    }

    sc.close();
  }

  static int solve(String S) {
    Map<Integer, Integer> stateToMaxCount = Map.of(0, 0);
    for (char ch : S.toCharArray()) {
      Map<Integer, Integer> nextStateToMaxCount = new HashMap<>();
      for (int state : stateToMaxCount.keySet()) {
        for (int i = 0; i < EVENTS.length; ++i) {
          int index = ((state & (1 << i)) == 0) ? 0 : 1;
          if (ch == EVENTS[i].charAt(index)) {
            int nextState = state ^ (1 << i);
            nextStateToMaxCount.put(
                nextState,
                Math.max(
                    nextStateToMaxCount.getOrDefault(nextState, 0),
                    stateToMaxCount.get(state) + ((ch == 'O' && i == 0) ? 1 : 0)));
          }
        }
      }

      stateToMaxCount = nextStateToMaxCount;
    }

    return stateToMaxCount.get(0);
  }
}
