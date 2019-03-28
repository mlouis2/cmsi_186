 public class PiSolver {
      private final static int DEFAULT_NUM_DARTS = 1000000;
      private final static int RADIUS = 1;
      public static void main (String[] args) {
           int numDarts = getNumDarts(args);
           int circleCount = throwDarts(numDarts);
           System.out.println(circleCount);
           System.out.println(numDarts);
           System.out.println(calculatePi(circleCount, numDarts));
      }
      public static int throwDarts(int numDarts) {
            int circleCount = 0;
            for (int dartsThrown = 0; dartsThrown < numDarts; dartsThrown++) {
                  if (throwDart() < RADIUS) {
                        circleCount++;
                  }
            }
            return circleCount;
      }
      public static int getNumDarts(String[] args) {
            int numDarts = DEFAULT_NUM_DARTS;
            if (args.length == 1){
                  try {
                        numDarts = Integer.parseInt(args[0]);
                        if (numDarts <= 0) {
                              throw new IllegalArgumentException();
                        }
                  } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Invalid input!");
                  }
            } else if (args.length > 1) {
                  throw new IllegalArgumentException("Too many arguments!");
            }
            return numDarts;
      }
      public static double throwDart() {
            double x = (Math.random() * (2));
            double y = (Math.random() * (2));
            return calculateDistanceFromCenter(x, y);
      }
      public static double calculateDistanceFromCenter(double x, double y) {
            return Math.sqrt(Math.pow(x-RADIUS, 2) + Math.pow(y-RADIUS, 2));
      }
      public static double calculatePi(int circleCount, int numDarts) {
            return (4 * ((double)circleCount / (double)numDarts));
      }
}
