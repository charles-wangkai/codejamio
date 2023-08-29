import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int N = sc.nextInt();
      int[] A = new int[N - 1];
      int[] B = new int[N - 1];
      int[] C = new int[N - 1];
      for (int i = 0; i < N - 1; ++i) {
        A[i] = sc.nextInt() - 1;
        B[i] = sc.nextInt() - 1;
        C[i] = sc.nextInt();
      }

      System.out.println(String.format("Case #%d: %d", tc, solve(A, B, C)));
    }

    sc.close();
  }

  static long solve(int[] A, int[] B, int[] C) {
    int N = A.length + 1;

    @SuppressWarnings("unchecked")
    List<Integer>[] edgeLists = new List[N];
    for (int i = 0; i < edgeLists.length; ++i) {
      edgeLists[i] = new ArrayList<>();
    }
    for (int i = 0; i < A.length; ++i) {
      edgeLists[A[i]].add(i);
      edgeLists[B[i]].add(i);
    }

    long result = Long.MAX_VALUE;
    for (int root = 0; root < N; ++root) {
      result = Math.min(result, search(A, B, C, edgeLists, -1, root).nonReturnCost);
    }

    return result;
  }

  static Outcome search(
      int[] A, int[] B, int[] C, List<Integer>[] edgeLists, int parent, int node) {
    long returnCost = 0;
    long minDelta = Long.MAX_VALUE;
    for (int edge : edgeLists[node]) {
      int other = (A[edge] == node) ? B[edge] : A[edge];
      if (other != parent) {
        Outcome subResult = search(A, B, C, edgeLists, node, other);
        returnCost += 2 * C[edge] + subResult.returnCost;
        minDelta = Math.min(minDelta, -C[edge] - subResult.returnCost + subResult.nonReturnCost);
      }
    }

    long nonReturnCost = returnCost + ((minDelta == Long.MAX_VALUE) ? 0 : minDelta);

    return new Outcome(returnCost, nonReturnCost);
  }
}

class Outcome {
  long returnCost;
  long nonReturnCost;

  Outcome(long returnCost, long nonReturnCost) {
    this.returnCost = returnCost;
    this.nonReturnCost = nonReturnCost;
  }
}
