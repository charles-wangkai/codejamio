import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int B = sc.nextInt();
      int[][] D = new int[B][];
      for (int i = 0; i < D.length; ++i) {
        D[i] = new int[i];
        for (int j = 0; j < D[i].length; ++j) {
          D[i][j] = sc.nextInt();
        }
      }

      System.out.println(String.format("Case #%d: %.9f", tc, solve(D)));
    }

    sc.close();
  }

  static double solve(int[][] D) {
    int B = D.length;

    for (int i = 0; i < B; ++i) {
      for (int j = 0; j < i; ++j) {
        if (D[i][j] != -1) {
          D[i][j] *= 2;
        }
      }
    }

    long[][] distances = new long[B][B];
    for (int i = 0; i < B; ++i) {
      for (int j = 0; j < B; ++j) {
        if (i < j) {
          distances[i][j] = D[j][i];
        } else if (i > j) {
          distances[i][j] = D[i][j];
        } else {
          distances[i][j] = 0;
        }

        if (distances[i][j] == -1) {
          distances[i][j] = Long.MAX_VALUE;
        }
      }
    }

    for (int k = 0; k < B; ++k) {
      for (int i = 0; i < B; ++i) {
        for (int j = 0; j < B; ++j) {
          if (distances[i][k] != Long.MAX_VALUE && distances[k][j] != Long.MAX_VALUE) {
            distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
          }
        }
      }
    }

    long result = Long.MAX_VALUE;
    for (int i = 0; i < B; ++i) {
      for (int j = 0; j < i; ++j) {
        if (D[i][j] != -1) {
          long minMaxDistance = -1;
          long lower = 0;
          long upper =
              (long) B
                  * Arrays.stream(D)
                      .mapToInt(row -> Arrays.stream(row).max().orElse(-1))
                      .max()
                      .getAsInt();
          while (lower <= upper) {
            long middle = (lower + upper) / 2;
            if (check(distances, i, j, D[i][j], middle)) {
              minMaxDistance = middle;
              upper = middle - 1;
            } else {
              lower = middle + 1;
            }
          }

          result = Math.min(result, minMaxDistance);
        }
      }
    }

    return result / 2.0;
  }

  static boolean check(long[][] distances, int v1, int v2, int edgeLength, long maxDistance) {
    int B = distances.length;

    List<Range> ranges = List.of(new Range(0, edgeLength));
    for (int i = 0; i < B; ++i) {
      List<Range> current = new ArrayList<>();
      if (distances[i][v1] <= maxDistance) {
        current.add(new Range(0, maxDistance - distances[i][v1]));
      }
      if (distances[i][v2] <= maxDistance) {
        current.add(new Range(edgeLength - (maxDistance - distances[i][v2]), edgeLength));
      }
      union(current);

      ranges = merge(ranges, current);
    }

    return !ranges.isEmpty();
  }

  static List<Range> merge(List<Range> ranges, List<Range> current) {
    List<Range> result = new ArrayList<>();
    for (Range r1 : current) {
      for (Range r2 : ranges) {
        long begin = Math.max(r1.begin, r2.begin);
        long end = Math.min(r1.end, r2.end);

        if (begin <= end) {
          result.add(new Range(begin, end));
        }
      }
    }

    return result;
  }

  static void union(List<Range> current) {
    if (current.size() == 2 && current.get(0).end >= current.get(1).begin) {
      Range r1 = current.remove(1);
      Range r0 = current.remove(0);

      current.add(new Range(r0.begin, r1.end));
    }
  }
}

class Range {
  long begin;
  long end;

  Range(long begin, long end) {
    this.begin = begin;
    this.end = end;
  }
}
