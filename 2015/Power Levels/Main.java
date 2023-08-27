import java.math.BigInteger;
import java.util.Scanner;

public class Main {
  static final int N = 9000;

  static int[] digitNums;

  public static void main(String[] args) {
    buildDigitNums();

    Scanner sc = new Scanner(System.in);

    int T = sc.nextInt();
    for (int tc = 1; tc <= T; ++tc) {
      int D = sc.nextInt();

      System.out.println(String.format("Case #%d: %s", tc, solve(D)));
    }

    sc.close();
  }

  static void buildDigitNums() {
    digitNums = new int[N];
    for (int i = 1; i < digitNums.length; ++i) {
      digitNums[i] = computeDigitNum(i);
    }
  }

  static int computeDigitNum(int e) {
    BigInteger product = BigInteger.ONE;
    for (int i = N; i >= 1; i -= e) {
      product = product.multiply(BigInteger.valueOf(i));
    }

    return product.toString().length();
  }

  static String solve(int D) {
    int e = 1;
    while (e != digitNums.length && digitNums[e] >= D) {
      ++e;
    }

    return (e == digitNums.length) ? "..." : String.format("IT'S OVER 9000%s", "!".repeat(e));
  }
}
