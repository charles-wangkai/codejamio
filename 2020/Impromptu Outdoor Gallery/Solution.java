import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int N = sc.nextInt();
      int[] X = new int[N];
      int[] Y = new int[N];
      for (int i = 0; i < N; ++i) {
        X[i] = sc.nextInt();
        Y[i] = sc.nextInt();
      }

      System.out.println(String.format("Case #%d: %d", tc, solve(X, Y)));
    }

    sc.close();
  }

  static long solve(int[] X, int[] Y) {
    int N = X.length;

    Point[] points =
        IntStream.range(0, N)
            .mapToObj(i -> new Point(X[i], Y[i]))
            .sorted(Comparator.comparing((Point p) -> p.y).thenComparing(p -> p.x))
            .toArray(Point[]::new);

    Map<Point, Integer> pointToIndex =
        IntStream.range(0, N).boxed().collect(Collectors.toMap(i -> points[i], i -> i));

    List<Line> lines = new ArrayList<>();
    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < N; ++j) {
        Line line = new Line(points[i], points[j]);
        int dx = line.getDx();
        int dy = line.getDy();

        if (dy > 0 || (dy == 0 && dx > 0)) {
          lines.add(line);
        }
      }
    }
    Collections.sort(
        lines,
        (l1, l2) ->
            -Long.signum(computeCrossProduct(new Point(0, 0), l1.getVector(), l2.getVector())));

    long result = Long.MAX_VALUE;
    for (Line line : lines) {
      int index1 = pointToIndex.get(line.p1);
      int index2 = pointToIndex.get(line.p2);
      int prevIndex = Math.min(index1, index2) - 1;
      int nextIndex = Math.max(index1, index2) + 1;

      if (prevIndex != -1 && nextIndex != N) {
        result =
            Math.min(
                result,
                computeDoubledArea(
                    new Point[] {
                      points[index1], points[prevIndex], points[index2], points[nextIndex]
                    }));
      }

      Point temp = points[index1];
      points[index1] = points[index2];
      points[index2] = temp;

      pointToIndex.put(points[index1], index1);
      pointToIndex.put(points[index2], index2);
    }

    return result;
  }

  static long computeCrossProduct(Point o, Point p1, Point p2) {
    return (long) (p1.x - o.x) * (p2.y - o.y) - (long) (p2.x - o.x) * (p1.y - o.y);
  }

  static long computeDoubledArea(Point[] polygon) {
    return Math.abs(
        IntStream.range(0, polygon.length)
            .mapToLong(
                i ->
                    (long) polygon[i].x * polygon[(i + 1) % polygon.length].y
                        - (long) polygon[i].y * polygon[(i + 1) % polygon.length].x)
            .sum());
  }
}

class Line {
  Point p1;
  Point p2;

  Line(Point p1, Point p2) {
    this.p1 = p1;
    this.p2 = p2;
  }

  int getDx() {
    return p2.x - p1.x;
  }

  int getDy() {
    return p2.y - p1.y;
  }

  Point getVector() {
    return new Point(getDx(), getDy());
  }
}

class Point {
  int x;
  int y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  @Override
  public boolean equals(Object obj) {
    Point other = (Point) obj;

    return x == other.x && y == other.y;
  }
}
