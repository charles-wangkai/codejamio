import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int R = sc.nextInt();
      int C = sc.nextInt();
      char[][] map = new char[R][C];
      for (int r = 0; r < R; ++r) {
        String line = sc.next();
        for (int c = 0; c < C; ++c) {
          map[r][c] = line.charAt(c);
        }
      }

      System.out.println(String.format("Case #%d: %d", tc, solve(map)));
    }

    sc.close();
  }

  static int solve(char[][] map) {
    int R = map.length;
    int C = map[0].length;

    int evenMinSum = Integer.MAX_VALUE;
    int evenMaxSum = Integer.MIN_VALUE;
    int evenMinDiff = Integer.MAX_VALUE;
    int evenMaxDiff = Integer.MIN_VALUE;
    int oddMinSum = Integer.MAX_VALUE;
    int oddMaxSum = Integer.MIN_VALUE;
    int oddMinDiff = Integer.MAX_VALUE;
    int oddMaxDiff = Integer.MIN_VALUE;
    for (int r = 0; r < R; ++r) {
      for (int c = 0; c < C; ++c) {
        if ((r + c) % 2 == 0) {
          evenMinSum = Math.min(evenMinSum, r + c);
          evenMaxSum = Math.max(evenMaxSum, r + c);
          evenMinDiff = Math.min(evenMinDiff, r - c);
          evenMaxDiff = Math.max(evenMaxDiff, r + c);
        } else {
          oddMinSum = Math.min(oddMinSum, r + c);
          oddMaxSum = Math.max(oddMaxSum, r + c);
          oddMinDiff = Math.min(oddMinDiff, r - c);
          oddMaxDiff = Math.max(oddMaxDiff, r + c);
        }
      }
    }

    int result = 0;
    Map<State, Integer> stateToNimber = new HashMap<>();
    for (int r = 0; r < R; ++r) {
      for (int c = 0; c < C; ++c) {
        if (map[r][c] == 'X'
            && (((r + c) % 2 == 0
                    && (computeNimber(
                                map, stateToNimber, evenMinSum, r + c - 2, evenMinDiff, r - c - 2)
                            ^ computeNimber(
                                map, stateToNimber, r + c + 2, evenMaxSum, evenMinDiff, r - c - 2)
                            ^ computeNimber(
                                map, stateToNimber, r + c + 2, evenMaxSum, r - c + 2, evenMaxDiff)
                            ^ computeNimber(
                                map, stateToNimber, evenMinSum, r + c - 2, r - c + 2, evenMaxDiff)
                            ^ computeNimber(
                                map, stateToNimber, oddMinSum, oddMaxSum, oddMinDiff, oddMaxDiff))
                        == 0)
                || ((r + c) % 2 != 0
                    && (computeNimber(
                                map, stateToNimber, oddMinSum, r + c - 2, oddMinDiff, r - c - 2)
                            ^ computeNimber(
                                map, stateToNimber, r + c + 2, oddMaxSum, oddMinDiff, r - c - 2)
                            ^ computeNimber(
                                map, stateToNimber, r + c + 2, oddMaxSum, r - c + 2, oddMaxDiff)
                            ^ computeNimber(
                                map, stateToNimber, oddMinSum, r + c - 2, r - c + 2, oddMaxDiff)
                            ^ computeNimber(
                                map,
                                stateToNimber,
                                evenMinSum,
                                evenMaxSum,
                                evenMinDiff,
                                evenMaxDiff))
                        == 0))) {
          ++result;
        }
      }
    }

    return result;
  }

  static int computeNimber(
      char[][] map,
      Map<State, Integer> stateToNimber,
      int minSum,
      int maxSum,
      int minDiff,
      int maxDiff) {
    int R = map.length;
    int C = map[0].length;

    State state = new State(minSum, maxSum, minDiff, maxDiff);
    if (!stateToNimber.containsKey(state)) {
      Set<Integer> nimbers = new HashSet<>();
      for (int r = 0; r < R; ++r) {
        for (int c = 0; c < C; ++c) {
          if (map[r][c] == 'X'
              && (r + c) % 2 == minSum % 2
              && r + c >= minSum
              && r + c <= maxSum
              && r - c >= minDiff
              && r - c <= maxDiff) {
            nimbers.add(
                computeNimber(map, stateToNimber, minSum, r + c - 2, minDiff, r - c - 2)
                    ^ computeNimber(map, stateToNimber, r + c + 2, maxSum, minDiff, r - c - 2)
                    ^ computeNimber(map, stateToNimber, r + c + 2, maxSum, r - c + 2, maxDiff)
                    ^ computeNimber(map, stateToNimber, minSum, r + c - 2, r - c + 2, maxDiff));
          }
        }
      }

      stateToNimber.put(state, mex(nimbers));
    }

    return stateToNimber.get(state);
  }

  static int mex(Set<Integer> nimbers) {
    for (int i = 0; ; ++i) {
      if (!nimbers.contains(i)) {
        return i;
      }
    }
  }
}

class State {
  int minSum;
  int maxSum;
  int minDiff;
  int maxDiff;

  State(int minSum, int maxSum, int minDiff, int maxDiff) {
    this.minSum = minSum;
    this.maxSum = maxSum;
    this.minDiff = minDiff;
    this.maxDiff = maxDiff;
  }

  @Override
  public int hashCode() {
    return Objects.hash(minSum, maxSum, minDiff, maxDiff);
  }

  @Override
  public boolean equals(Object obj) {
    State other = (State) obj;

    return minSum == other.minSum
        && maxSum == other.maxSum
        && minDiff == other.minDiff
        && maxDiff == other.maxDiff;
  }
}
