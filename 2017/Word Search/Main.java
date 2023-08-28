import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
  static final int SIZE = 15;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int D = sc.nextInt();
      int N = sc.nextInt();

      System.out.println(String.format("Case #%d:\n%s", tc, solve(D, N)));
    }

    sc.close();
  }

  static String solve(int D, int N) {
    char[][] grid = new char[SIZE][SIZE];
    for (int r = 0; r < SIZE; ++r) {
      for (int c = 0; c < SIZE; ++c) {
        grid[r][c] = (c % 2 == 0) ? 'O' : '/';
      }
    }

    if (N != 0) {
      grid[0][0] = 'I';
      grid[0][1] = 'O';
      --N;
    }

    List<Element> elements = new ArrayList<>();
    for (int r = 0; r < SIZE; ++r) {
      for (int c = 0; c < SIZE; c += 4) {
        if (c == 0) {
          if (r >= 2 && r <= SIZE - 3) {
            elements.add(new Element(r, c, 3));
          } else if (r != 0) {
            elements.add(new Element(r, c, 2));
          }
        } else {
          if (r >= 2 && r <= SIZE - 3) {
            elements.add(new Element(r, c, 6));
          } else {
            elements.add(new Element(r, c, 4));
          }
        }
      }
    }

    Collections.sort(elements, Comparator.comparing((Element e) -> e.count).reversed());
    for (Element element : elements) {
      if (N >= element.count) {
        grid[element.r][element.c] = 'I';
        N -= element.count;
      }
    }

    if (N == 1) {
      grid[0][1] = '/';
    }

    return Arrays.stream(grid).map(String::new).collect(Collectors.joining("\n"));
  }
}

class Element {
  int r;
  int c;
  int count;

  Element(int r, int c, int count) {
    this.r = r;
    this.c = c;
    this.count = count;
  }
}
