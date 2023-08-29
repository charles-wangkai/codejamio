import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      String B = sc.next();

      System.out.println(String.format("Case #%d: %s", tc, solve(B)));
    }

    sc.close();
  }

  static String solve(String B) {
    boolean iTurn = true;
    int leftIndex = 0;
    int rightIndex = B.length() - 1;

    while (leftIndex <= rightIndex) {
      char left = B.charAt(leftIndex);
      char right = B.charAt(rightIndex);

      if (iTurn) {
        if (left == 'O' && right == 'O') {
          break;
        } else if (left == 'I' && right == 'O') {
          ++leftIndex;
          iTurn = false;
        } else if (left == 'O' && right == 'I') {
          --rightIndex;
          iTurn = false;
        } else {
          int nextLeftIndex = leftIndex;
          while (nextLeftIndex <= rightIndex
              && B.charAt(nextLeftIndex) == 'I'
              && nextLeftIndex + 1 <= rightIndex
              && B.charAt(nextLeftIndex + 1) == 'O') {
            nextLeftIndex += 2;
          }
          if (nextLeftIndex == rightIndex) {
            leftIndex = rightIndex + 1;
            iTurn = false;
          } else {
            char nextLeft = B.charAt(nextLeftIndex);
            int leftLength = nextLeftIndex - leftIndex;

            int nextRightIndex = rightIndex;
            while (nextRightIndex >= leftIndex
                && B.charAt(nextRightIndex) == 'I'
                && nextRightIndex - 1 >= leftIndex
                && B.charAt(nextRightIndex - 1) == 'O') {
              nextRightIndex -= 2;
            }
            char nextRight = B.charAt(nextRightIndex);
            int rightLength = rightIndex - nextRightIndex;

            if (nextLeft == 'O' && nextRight == 'O') {
              leftIndex = nextLeftIndex;
              rightIndex = nextRightIndex;
            } else if (nextLeft == 'I' && nextRight == 'O') {
              leftIndex = nextLeftIndex + 1;
              iTurn = false;
            } else if (nextLeft == 'O' && nextRight == 'I') {
              rightIndex = nextRightIndex - 1;
              iTurn = false;
            } else if (leftLength <= rightLength) {
              leftIndex = nextLeftIndex + 1;
              iTurn = false;
            } else {
              rightIndex = nextRightIndex - 1;
              iTurn = false;
            }
          }
        }
      } else {
        if (left == 'I' && right == 'I') {
          break;
        } else if (left == 'O' && right == 'I') {
          ++leftIndex;
          iTurn = true;
        } else if (left == 'I' && right == 'O') {
          --rightIndex;
          iTurn = true;
        } else {
          int nextLeftIndex = leftIndex;
          while (nextLeftIndex <= rightIndex
              && B.charAt(nextLeftIndex) == 'O'
              && nextLeftIndex + 1 <= rightIndex
              && B.charAt(nextLeftIndex + 1) == 'I') {
            nextLeftIndex += 2;
          }
          if (nextLeftIndex == rightIndex) {
            leftIndex = rightIndex + 1;
            iTurn = true;
          } else {
            char nextLeft = B.charAt(nextLeftIndex);
            int leftLength = nextLeftIndex - leftIndex;

            int nextRightIndex = rightIndex;
            while (nextRightIndex >= leftIndex
                && B.charAt(nextRightIndex) == 'O'
                && nextRightIndex - 1 >= leftIndex
                && B.charAt(nextRightIndex - 1) == 'I') {
              nextRightIndex -= 2;
            }
            char nextRight = B.charAt(nextRightIndex);
            int rightLength = rightIndex - nextRightIndex;

            if (nextLeft == 'I' && nextRight == 'I') {
              leftIndex = nextLeftIndex;
              rightIndex = nextRightIndex;
            } else if (nextLeft == 'O' && nextRight == 'I') {
              leftIndex = nextLeftIndex + 1;
              iTurn = true;
            } else if (nextLeft == 'I' && nextRight == 'O') {
              rightIndex = nextRightIndex - 1;
              iTurn = true;
            } else if (leftLength <= rightLength) {
              leftIndex = nextLeftIndex + 1;
              iTurn = true;
            } else {
              rightIndex = nextRightIndex - 1;
              iTurn = true;
            }
          }
        }
      }
    }

    return String.format("%c %d", iTurn ? 'O' : 'I', rightIndex - leftIndex + 2);
  }
}
