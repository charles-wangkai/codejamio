import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int L = sc.nextInt();
      long[] N = new long[L];
      int[] E = new int[L];
      for (int i = 0; i < L; ++i) {
        N[i] = sc.nextLong();
        E[i] = sc.nextInt();
      }

      System.out.println(String.format("Case #%d: %d", tc, solve(N, E)));
    }

    sc.close();
  }

  static long solve(long[] N, int[] E) {
    int L = N.length;

    long rest = 0;
    for (int i = 0; i < L; ++i) {
      rest = Math.max(0, rest - N[i] * E[i]) + N[i];
    }

    return Math.max(rest, E[L - 1] + 1);
  }
}
