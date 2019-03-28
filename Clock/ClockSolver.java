public class ClockSolver {

     public static final double[] TARGET_ANGLES = { 90.0, 180.0, 270.0 };
     public static final double DEFAULT_TIME_SLICE = 60.0;

     public static void main(String[] args) {
          double timeSlice = DEFAULT_TIME_SLICE;
          if (args.length != 0) {
               try {
                    if (validTimeSlice(Double.parseDouble(args[0]))) {
                         timeSlice = Double.parseDouble(args[0]);
                    } else {
                         throw new IllegalArgumentException();
                    }
               } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input!");
                    return;
               }
          }

          Clock clock = new Clock(0, 0, 0.0, timeSlice);

          double currentAngle = 0;
          double angleChange = findAngleChange(timeSlice);

          while (clock.hours() < 12 || (clock.hours() == 12 && (clock.minutes() == 0 && clock.seconds() == 0.0))) {
               clock.advanceTime();
               currentAngle = findAngle(clock);
               if (angleInRange(currentAngle, angleChange)) {
                    System.out.println(clock.toString());
               }
          }
     }

     public static boolean validTimeSlice(double timeSlice) {
          return (timeSlice < 1800.0 && timeSlice > 0.0);
     }
     public static double findAngle(Clock clock) {
          double minuteHandAngle = (clock.minutes() * 6) + (clock.seconds() * (0.1));
          double hourHandAngle = (clock.hours() * 30) + (clock.minutes() * 0.5) + (clock.seconds() * (0.5 / 60.0));
          return Math.abs(minuteHandAngle - hourHandAngle);
     }
     public static boolean angleInRange(double angle, double angleChange) {
          for (double target : TARGET_ANGLES) {
               if (angle <= target + (angleChange / 2) && angle >= target - (angleChange / 2)) {
                    return true;
               }
          }
          return false;
     }
     public static double findAngleChange(double timeSlice) {
          Clock sampleClock = new Clock(0, 0, 0.0, timeSlice);
          sampleClock.advanceTime();
          return findAngle(sampleClock);
     }
}
