package random;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/ctci-big-o
 */

public class TimeComplexityPrimality {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int p = scanner.nextInt();

    for (int i = 0; i < p; i++) {
      int input = scanner.nextInt();

      boolean isPrime = isPrime(input);
      System.out.println(input + ": " + (isPrime ? "Prime" : "Not prime"));
    }

  }

  private static boolean isPrime(int input) {
    if (input < 2) return false;
    if (input == 2) return true;
    if (input % 2 == 0) return false;

    for (int i = 3; i <= Math.sqrt(input); i += 2) {
      if (input % i == 0) return false;
    }
    return true;
  }
}
