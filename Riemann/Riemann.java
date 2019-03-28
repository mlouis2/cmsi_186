public class Riemann {
     public static void main(String[] args) {
          if ( ! validArgs(args)) {
               throw new IllegalArgumentException("Invalid input!");
          }
          double lowerBound = Double.parseDouble(args[args.length - 3]);
          double upperBound = Double.parseDouble(args[args.length - 2]);
          double percentError = Double.parseDouble(args[args.length - 1]);
          double[] polynomialCoefficients = new double[args.length - 3];
          for (int i = 0; i < polynomialCoefficients.length; i++) {
               polynomialCoefficients[i] = Double.parseDouble(args[i]);
          }
          System.out.println(computeIntegral(polynomialCoefficients, lowerBound, upperBound, percentError));
     }
     public static boolean validArgs(String[] args) {
          if (args.length > 3) {
               try {
                    for (int i  = 0; i  < args.length; i++) {
                         Double.parseDouble(args[i]);
                    }
               } catch (NumberFormatException e) {
                    return false;
               }
               double percent = Double.parseDouble(args[args.length - 1]);
               return ( validBounds(args) && validPercent(percent));
          }
          return false;
     }
     public static boolean validBounds(String[] args) {
          double lowerBound = Double.parseDouble(args[args.length - 3]);
          double upperBound = Double.parseDouble(args[args.length - 2]);
          return (lowerBound < upperBound);
     }
     public static boolean validPercent(double percent) {
          return (percent > 0.0);
     }
     public static double computeIntegral(double[] polynomialCoefficients, double lowerBound, double upperBound, double percentError) {
          int numRectangles = 1;
          double areaOfRectangles = 0.0;
          double newArea = 0.0;
          double deltaX = 0.0;
          do {
               newArea = areaOfRectangles;
               areaOfRectangles = 0.0;
               deltaX = ((upperBound - lowerBound) / numRectangles);
               for (int i = 0; i < numRectangles; i++) {
                    areaOfRectangles = areaOfRectangles + computeArea(lowerBound + (deltaX * i), lowerBound + (deltaX * i) + deltaX, polynomialCoefficients);
               }
               numRectangles = numRectangles * 2;
          } while ( ! withinBounds(areaOfRectangles, newArea, percentError));
          return newArea;
     }
     public static double computeArea(double x1, double x2, double[] polynomialCoefficients) {
          double height = evaluateFunctionAtX(polynomialCoefficients, returnMidpoint(x1, x2));
          double width = (Math.abs(x2 - x1));
          return (width * height);
     }
     public static double returnMidpoint(double x1, double x2) {
          return ((x1 + x2) / 2.0);
     }
     public static boolean withinBounds(double area, double newArea, double percentError) {
          return (Math.abs((area - newArea)) <= Math.abs((area * (percentError / 100.0))));
     }
     public static double evaluateFunctionAtX(double[] polynomialCoefficients, double x) {
          double y = 0.0;
          for (int i  = 0; i < polynomialCoefficients.length; i++) {
               y = y + (polynomialCoefficients[i] * Math.pow(x, i));
          }
          return y;
     }
}
