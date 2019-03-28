// public class GCD {
//      public static void main(String[] args) {
//           int ans = 0;
//           int divisor = getSmallest(args);
//
//           boolean gcdFound = false;
//
//           while (!gcdFound) {
//                boolean isGCD = true;
//                for (int y = 0; y < args.length; y++) {
//                     if (Integer.parseInt(args[y]) % divisor != 0) {
//                          isGCD = false;
//                     }
//                }
//                if (isGCD) {
//                     System.out.println("The Greatest Common Divisor is " + divisor);
//                     gcdFound = true;
//                }
//                divisor--;
//           }
//
//      }
//      public static int getSmallest(String[] nums) {
//           String currentSmallest = nums[nums.length - 1];
//           for (int x = 0; x < nums.length - 1; x++) {
//                if (Integer.parseInt(nums[x]) < Integer.parseInt(currentSmallest)) {
//                     currentSmallest = nums[x];
//                }
//           }
//           return Integer.parseInt(currentSmallest);
//      }
// }

import java.util.Scanner;

public class GCD {
     public static void main(String[] args) {
          //long a = Long.parseLong(args[0]);
          //long b = Long.parseLong(args[1]);
          Scanner scanner = new Scanner(System.in);
          System.out.println("Enter first number.");
          long a = scanner.nextLong();
          System.out.println("Enter second number.");
          long b = scanner.nextLong();

          System.out.println("The GCD is " + HelpfulMethods.gcd(a, b));

     }
}
