public class ChangeMaker {
     /**
     * Makes change out with given coin denominations and a given amount to be made
     * @param args A list of coin denominations, ending with an amount of change to be made
     */
     public static void main(String[] args) {
          int amount;
          int[] coins;
          if (isValid(args)) {
               coins = new int[args.length - 1];
               for (int x = 0; x < args.length - 1; x++) {
                    coins[x] = Integer.parseInt(args[x]);
               }
               amount = Integer.parseInt(args[args.length - 1]);
          } else {
               throw new IllegalArgumentException("Invalid input!");
          }
          Tuple denominations = new Tuple(coins);
          System.out.println(formatResult(makeChange(amount, denominations), denominations));
     }
     /**
     * Checks if the array of arguments is greater than two, if they can be parsed into integers,
     * and if none of the coin arguments are the same.
     * @param args An array of string arguments that need to be validated.
     */
     public static boolean isValid(String[] args) {
          if (args.length < 2) {
               return false;
          }
          try {
               for (int x = 0; x < args.length; x++) {
                    if (Integer.parseInt(args[x]) <= 0) {
                         return false;
                    }
               }
               for (int x = 0; x < args.length; x++) {
                    for (int y = x + 1; y < args.length - 1; y++) {
                         if (args[x].equals(args[y])) {
                              return false;
                         }
                    }
               }
               return true;
          } catch(NumberFormatException e) {
               return false;
          } catch(NullPointerException e) {
               return false;
          }
     }
     /**
     * Returns a Tuple with the optimal amount of change to create the given amount
     * @param amount The amount of change to be made
     * @param coins A tuple containing the coin denominations that the change is to be made with
     */
     public static Tuple makeChange(int amount, Tuple coins) {
          Tuple[][] table = createTable(amount, coins);
          for (int row = 0; row < coins.length(); row++) {
               for (int col = 1; col < amount + 1; col++) {
                    if (col - coins.getElement(row) >= 0 && table[row][col - coins.getElement(row)] != null){
                         Tuple newTuple = new Tuple(coins.length());
                         newTuple.setElement(row, 1);
                         table[row][col] = newTuple.add(table[row][col - coins.getElement(row)]);
                    }
                    if (row > 0) {
                         table[row][col] = getOptimalTuple(table[row][col], table[row - 1][col]);
                    }
               }
          }
          return table[coins.length() - 1][amount];
     }
     /**
     * Returns the smaller of two Tuples, and if either Tuple is null then it returns the other.
     * @param tuple1 The first Tuple to be compared
     * @param tuple2 The second Tuple to be compared
     */
     public static Tuple getOptimalTuple(Tuple tuple1, Tuple tuple2) {
          if (tuple1 == null) {
               return tuple2;
          } else if (tuple2 == null) {
               return tuple1;
          } else {
               return (tuple2.sum() < tuple1.sum() ? tuple2 : tuple1);
          }
     }
     /**
     * Returns a 2D array of Tuples as wide as the amount of coins to be made and as tall as the number of coin denominations
     * @param amount The amount of coins to be made
     * @param coins The coin denominations that the change is to be made with
     */
     public static Tuple[][] createTable(int amount, Tuple coins) {
          Tuple[][] table = new Tuple[coins.length()][amount + 1];
          for (int row = 0; row < coins.length(); row++) {
               table[row][0] = new Tuple(coins.length());
          }
          return table;
     }
     /**
     * Formats the resulting Tuple
     * @param answer The Tuple that contains the optimal way to make change
     * @param denominations The Tuple that contains the coin denominations
     */
     public static String formatResult(Tuple answer, Tuple denominations) {
          if (answer == null) {
               return "IMPOSSIBLE";
          } else {
               String result = "";
               for (int x = 0; x < denominations.length(); x++) {
                    result = result + answer.getElement(x);
                    result = result + " " + denominations.getElement(x) + "-cent coins";
                    result = result + "\n";
               }
               result = result + answer.sum() + " total coins";
               return result;
          }
     }
}
