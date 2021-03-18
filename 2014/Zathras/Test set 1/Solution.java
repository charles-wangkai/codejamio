import java.util.Scanner;

public class Solution {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int A = sc.nextInt();
      int B = sc.nextInt();
      int alpha = sc.nextInt();
      int beta = sc.nextInt();
      int Y = sc.nextInt();

      System.out.println(String.format("Case #%d: %s", tc, solve(A, B, alpha, beta, Y)));
    }

    sc.close();
  }

  static String solve(int A, int B, int alpha, int beta, int Y) {
    for (int i = 0; i < Y; ++i) {
      int offspring = Math.min(A, B) * 2 / 100;
      int aBorn = offspring * alpha / 100;
      int bBorn = offspring * beta / 100;
      int rest = offspring - aBorn - bBorn;
      aBorn += rest / 2;
      bBorn += rest - rest / 2;

      A += aBorn - A / 100;
      B += bBorn - B / 100;
    }

    return String.format("%d %d", A, B);
  }
}
