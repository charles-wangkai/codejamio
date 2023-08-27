import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int K = sc.nextInt();
      int V = sc.nextInt();

      System.out.println(String.format("Case #%d: %d", tc, solve(K, V)));
    }

    sc.close();
  }

  static long solve(int K, int V) {
    long result = 0;
    if (V > K - V) {
      for (int a = 0; a <= K; ++a) {
        for (int b = 0; b <= K; ++b) {
          result += computeCNum(K, V, a, b);
        }
      }
    } else {
      for (int a = 0; a < V; ++a) {
        for (int b = 0; b <= a + V; ++b) {
          result += computeCNum(K, V, a, b);
        }
      }
      for (int a = K; a > K - V; --a) {
        for (int b = K; b >= a - V; --b) {
          result += computeCNum(K, V, a, b);
        }
      }

      int unitCount = 0;
      for (int d1 = -V; d1 <= V; ++d1) {
        for (int d2 = -V; d2 <= V; ++d2) {
          if (Math.abs(d1 - d2) <= V) {
            ++unitCount;
          }
        }
      }
      result += (K - V - V + 1L) * unitCount;
    }

    return result;
  }

  static int computeCNum(int K, int V, int a, int b) {
    if (Math.abs(a - b) > V) {
      return 0;
    }

    int minC = Math.max(0, Math.max(a, b) - V);
    int maxC = Math.min(K, Math.min(a, b) + V);

    return Math.max(0, maxC - minC + 1);
  }
}
