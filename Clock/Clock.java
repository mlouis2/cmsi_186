import java.text.DecimalFormat;

public class Clock {
     private int hours = 0;
     private int minutes = 0;
     private double seconds = 0.0;
     private double timeSlice = 0.0;

     public static void main(String[] args) {
          System.out.println("Testing AdvanceTime Method...");
          Clock clock1 = new Clock(0, 0, 0.0, 60.0);
          clock1.advanceTime();
          System.out.println(clock1.hours() == 0);
          System.out.println(clock1.minutes() == 1);
          Clock clock2 = new Clock(0, 0, 0.0, 600.1);
          for (int x = 0; x < 6; x++) {
               clock2.advanceTime();
          }
          System.out.println(clock2.hours() == 1);
          System.out.println(clock2.seconds() > (0.6 - .00001) && clock2.seconds() < (0.6 + .00001));
          Clock clock3 = new Clock(3, 2, 2.2, 500.0);
          clock3.advanceTime();
          System.out.println(clock3.seconds() > (22.2 - .00001) && clock3.seconds() < (22.2 + .00001));
          System.out.println(clock3.minutes() == 10);
          Clock clock4 = new Clock(12, 59, 59.99999, 60);
          clock4.advanceTime();
          System.out.println(clock4.hours() == 1);
          System.out.println("Testing Clock four-argument constructor...");
          try {
               Clock clock5 = new Clock(-1, 0, 0.0, 60.0);
          }
          catch(Exception e) {
               System.out.println("true");
          }
          try {
               Clock clock6= new Clock(13, 61, 92.3, 60.0);
          }
          catch(Exception e) {
               System.out.println("true");
          }
     }
     public Clock() {
          this.hours = 0;
          this.minutes = 0;
          this.seconds = 0;
          this.timeSlice = 60;
     }
     public Clock(int hours, int minutes, double seconds, double timeSlice) {
          if ((hours > 12 || hours < 0) || (minutes > 59 || minutes < 0) || (!(seconds < 60) || seconds < 0.0)) {
               throw new IllegalArgumentException("Invalid input!");
          }
          this.hours = hours;
          this.minutes = minutes;
          this.seconds = seconds;
          this.timeSlice = timeSlice;
     }
     public void advanceTime() {
          this.seconds += this.timeSlice;
          if ((int)this.seconds / 60 > 0) {
               this.minutes += ((int)this.seconds / 60);
               this.seconds = this.seconds % 60;
          }
          if (this.minutes / 60 > 0) {
               this.hours += (this.minutes / 60);
               this.minutes = this.minutes % 60;
          }
          if (this.hours > 12) {
               this.hours = 1;
          }
     }
     public int hours() {
          return this.hours;
     }
     public int minutes() {
          return this.minutes;
     }
     public double seconds() {
          return this.seconds;
     }
     public String toString() {
          DecimalFormat format = new DecimalFormat((String) "00");
          return format.format(this.hours) + ":" + format.format(this.minutes) + ":" + (this.seconds);
     }
}
