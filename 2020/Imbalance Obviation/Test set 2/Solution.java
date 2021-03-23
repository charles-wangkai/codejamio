import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int N = sc.nextInt();
      int[] A = new int[N];
      for (int i = 0; i < A.length; ++i) {
        A[i] = sc.nextInt();
      }

      System.out.println(String.format("Case #%d: %s", tc, solve(A)));
    }

    sc.close();
  }

  static String solve(int[] A) {
    int N = A.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[N];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < N; i += 2) {
      adjLists[i].add(i + 1);
      adjLists[i + 1].add(i);
    }
    for (int i = 0; i < A.length; i += 2) {
      adjLists[A[i] - 1].add(A[i + 1] - 1);
      adjLists[A[i + 1] - 1].add(A[i] - 1);
    }

    char[] placement = new char[A.length];
    for (int i = 0; i < placement.length; i += 2) {
      if (placement[i] == 0) {
        fill(placement, adjLists, i, 'L');
      }
    }

    return new String(placement);
  }

  static void fill(char[] placement, List<Integer>[] adjLists, int v, char side) {
    placement[v] = side;

    for (int adj : adjLists[v]) {
      if (placement[adj] == 0) {
        fill(placement, adjLists, adj, (char) ('L' + 'R' - side));
      }
    }
  }
}
