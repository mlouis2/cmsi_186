public class CalendarStuff {
     public static void main (String[] args) {

          System.out.println("Testing \"isLeapYear()\" function...");
          System.out.println(!isLeapYear(1700));
          System.out.println(!isLeapYear(2500));
          System.out.println(!isLeapYear(1900));
          System.out.println(!isLeapYear(1991));
          System.out.println(isLeapYear(1992));
          System.out.println(isLeapYear(2000));
          System.out.println(!isLeapYear(2003));
          System.out.println("");

          System.out.println("Testing \"daysInMonth()\" function...");
          System.out.println(daysInMonth(12, 2000) == 31);
          System.out.println(daysInMonth(6, 2000) == 30);
          System.out.println(daysInMonth(2, 2000) == 29);
          System.out.println(daysInMonth(2, 2001) == 28);
          System.out.println("");

          System.out.println("Testing \"daysBetween()\" function...");
          System.out.println(daysBetween(1, 1, 2000, 1, 1, 2000) == 0);
          System.out.println(daysBetween(1, 1, 2000, 2, 1, 2000) == 31);
          System.out.println(daysBetween(1, 1, 2000, 1, 2, 2000) == 1);
          System.out.println(daysBetween(1, 1, 2000, 1, 1, 2001) == 366);
          System.out.println(daysBetween(3, 15, 2011, 2, 1, 2011) == 42);
          System.out.println(daysBetween(1, 1, 2000, 1, 1, 2065) == 23742);
          try {
               System.out.println(daysBetween(4, 2, 2001, 4, 2, -1020));
          }
          catch(Exception e) {
               System.out.println("true");
          }
          try {
               System.out.println(daysBetween(4, 2, 2001, 4, 500, 2020));
          }
          catch(Exception e) {
               System.out.println("true");
          }
     }

     public static boolean isLeapYear(long year) {
          if (year % 4 == 0 && year > 1582) {
               if (year % 100 == 0) {
                    return (year % 400 == 0);
               }
               return true;
          }
          return false;
     }

     public static long daysInMonth(long month, long year) {
          long[] daysInMonths = {31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
          return daysInMonths[(int)month - 1];
     }

     public static long daysBetween(long month0, long day0, long year0, long month1, long day1, long year1) {
          if ((!isValidMonth(month0) || !isValidMonth(month1)) || (!isValidYear(year0) || !isValidYear(year1)) || (!isValidDays(day0, month0, year0) || !isValidDays(day1, month1, year1))) {
               throw new IllegalArgumentException("Invalid input!");
          }
          if (year1 > year0) {
               return daysBetween(month1, day1, year1, month0, day0, year0);
          }
          long daysFromYears = 0, daysFromMonths = 0, daysFromDays = 0;
          for (int i = 0; i < (year0 - year1); i++) {
               if (isLeapYear(year1 + i)) {
                    daysFromYears += 366;
               }
               else {
                    daysFromYears += 365;
               }
          }
          if (Math.abs(month0 - month1) > 0) {
               if (month0 > month1) {
                    for (int i = 0; i < (month0 - month1); i++) {
                         daysFromMonths += daysInMonth(month1 + i, year1);
                    }
               }
               else {
                    for (int i = 0; i < (month1 - month0); i++) {
                         daysFromMonths += daysInMonth(month0 + i, year0);
                    }
               }
          }
          daysFromDays = Math.abs(day0 - day1);
          return (daysFromYears + daysFromMonths + daysFromDays);
     }

     public static boolean isValidMonth(long month) {
          return (month > 0 && month < 13);
     }

     public static boolean isValidYear(long year) {
          return (year > 0);
     }

     public static boolean isValidDays(long days, long month, long year) {
          return (days < daysInMonth(month, year) && days > -1);
     }

     }
