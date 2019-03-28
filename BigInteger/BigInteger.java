public class BigInteger {

     private String value;
     private boolean negative;
     public static final BigInteger ZERO = new BigInteger("0");
     public static final BigInteger ONE = new BigInteger("1");
     public static final BigInteger TEN = new BigInteger("10");

     /**
     * Contains tests for the methods and constructors of the BigInteger Class
     * @param args An array of strings that is passed in when the program is run
     */
     public static void main(String[] args) {
          testConstructors();
          testIsNegative();
          testEquals();
          testAdd();
          testSubtract();
          testHalvedAndDoubled();
          testMultiply();
          testDivide();
          testRemainder();
     }

     /**
     * One-argument constructor for BigInteger.
     * Interprets the string value and throws an IllegalArgumentException if there's a problem.
     * @param value The value of the BigInteger
     */
     public BigInteger(String value) {
          try {
               this.value = interpretValue(value.trim());
          } catch (IllegalArgumentException e) {
               throw new IllegalArgumentException("Invalid input!");
          }
     }
     /**
     * Two-argument constructor for BigInteger.
     * Interprets the string value and throws an IllegalArgumentException if there's a problem.
     * @param value The value of the BigInteger
     * @param isNegative Whether or not the BigInteger passed in is negative.
     */
     public BigInteger(String value, boolean isNegative) {
          try {
               this.value = interpretValue(value.trim());
               this.negative = isNegative;
          } catch (IllegalArgumentException e) {
               throw new IllegalArgumentException("Invalid input!");
          }
     }
     /**
     * Returns a string representation of the BigInteger.
     */
     public String toString() {
          if (this.isNegative()) {
               return "-" + this.value;
          }
          return this.value;
     }
     /**
     * Returns a string representation of the value of the BigInteger, without negative signs.
     */
     public String getValue() {
          return this.value;
     }
     /**
     * Sets the value of a BigInteger.
     * @param value The value that is to be set.
     */
     public void setValue(String value) {
          this.value = value;
     }
     /**
     * Returns whether or not the BigInteger is negative.
     */
     public boolean isNegative() {
          return this.negative;
     }
     /**
     * If the BigInteger is positive, this returns a negative version.
     * If the BigInteger is negative, this returns a positive version.
     */
     public BigInteger negate() {
          return new BigInteger(this.value, ! this.negative);
     }
     /**
     * Returns a positive version of a BigInteger.
     */
     public BigInteger absoluteValue() {
          return new BigInteger(this.value, false);
     }
     /**
     * Inteprets a string value of a BigInteger by trimming leading zeroes, removing negative signs, etc.
     * Throws an error if the value is invalid.
     * @param value Value passed into the BigInteger constructor that is to be interpreted.
     */
     public String interpretValue(String value) {
          this.negative = false;
          if (value instanceof String && value.length() > 0) {
               if ((value.charAt(0) != '-' && value.charAt(0) != '+') && ( ! Character.isDigit(value.charAt(0)))) {
                    throw new IllegalArgumentException();
               }
               for (int x = 1; x < value.length(); x++) {
                    if (! Character.isDigit(value.charAt(x))) {
                         throw new IllegalArgumentException();
                    }
               }
          } else {
               throw new IllegalArgumentException();
          }
          if (value.charAt(0) == '-') {
               this.negative = true;
          }
          value = removeSignsAndLeadingZeroes(value);
          return value.trim();
     }
     /**
     * Returns a string without signs (+/-) and without leading zeroes.
     * @param value The string value that is being interpreted in the interpretValue method.
     */
     public static String removeSignsAndLeadingZeroes(String value) {
          String result = value;
          while (result.length() > 2 && (result.charAt(0) == '-' && result.charAt(1) == '0')) {
               result = result.substring(0, 1) + result.substring(2, result.length());
          }
          if (result.charAt(0) == '+' || result.charAt(0) == '-') {
               result = result.substring(1, result.length());
          }
          while (result.length() > 1 && result.charAt(0) == '0') {
                result = result.substring(1, result.length());
          }
          return result;
     }
     /**
     * Returns whether or not two BigIntegers are equal.
     * @param obj The BigInteger that this is being compared to.
     */
     public boolean equals(Object obj) {
          if (obj instanceof BigInteger) {
               BigInteger bigInt = (BigInteger)obj;
               return (this.toString().equals(bigInt.toString()));
          } else {
               return false;
          }
     }
     /**
     * Adds two BigIntegers and returns the result.
     * @param operand The BigInteger to be added.
     */
     public BigInteger add(BigInteger operand) {
          String ans = "";
          String x = "";
          String y = "";
          if (this.isNegative() && ! operand.isNegative()) {
               return operand.subtract(this.negate());
          } else if ( ! this.isNegative() && operand.isNegative()) {
               return this.subtract(operand.negate());
          } else {
               x = this.value;
               y = operand.getValue();
          }
          int temp = 0;
          boolean carry = false;
          while (x.length() > 0 || y.length() > 0) {
               if (x.length() != 0 && y.length() != 0) {
                    temp = (Character.getNumericValue(x.charAt(x.length() - 1)) + Character.getNumericValue(y.charAt(y.length() - 1)));
               } else if (x.length() == 0) {
                    temp = Character.getNumericValue(y.charAt(y.length() - 1));
               } else {
                    temp = Character.getNumericValue(x.charAt(x.length() - 1));
               }
               if (carry) {
                    temp = temp + 1;
               }
               carry = false;
               if (temp > 9) {
                    carry = true;
                    if (!(x.length() < 2 && y.length() < 2)) {
                         temp = temp - 10;
                    }
               }
               ans = temp + ans;
               if (x.length() > 0) {
                    x = x.substring(0, x.length() - 1);
               }
               if (y.length() > 0) {
                    y = y.substring(0, y.length() - 1);
               }
          }
          if (this.isNegative() && operand.isNegative()) {
               ans = "-" + ans;
          }
          return new BigInteger(ans);
     }
     /**
     * Subtracts one BigInteger from another and returns the result.
     * @param operand The BigInteger to be subtracted.
     */
     public BigInteger subtract(BigInteger operand) {
          if (this.isNegative() || operand.isNegative()) {
               return this.add(operand.negate());
          }

          BigInteger x = ZERO;
          BigInteger y = ZERO;
          if (this.lessThan(operand)) {
               x = operand;
               y = this;
          } else {
               x = this;
               y = operand;
          }
          if (this.equals(operand)) {
               return ZERO;
          }

          String xValue = x.getValue();
          String yValue = y.getValue();
          while (xValue.length() > yValue.length()) {
               yValue = "0" + yValue;
          }
          String[] xValues = xValue.split("");
          String[] yValues = yValue.split("");
          int[] xNums = new int[xValues.length];
          int[] yNums = new int[yValues.length];

          for (int i = 0; i < xValues.length; i++) {
               xNums[i] = Integer.parseInt(xValues[i]);
               yNums[i] = Integer.parseInt(yValues[i]);
          }

          while (! validToSubtract(xNums, yNums)) {
               int temp = 0;
               for (int index = 0; index < xNums.length; index++) {
                    if (yNums[index] > xNums[index]) {
                         temp = index - 1;
                         while (xNums[temp] == 0) {
                              temp--;
                         }
                         while (temp < index) {
                              xNums[temp]--;
                              xNums[temp + 1] += 10;
                              temp++;
                         }
                    }
               }
          }

          String ans = "";
          for (int index = 0; index < xNums.length; index++) {
               ans = ans + (xNums[index] - yNums[index]);
          }
          while (ans.charAt(0) == '0') {
               ans = ans.substring(1, ans.length());
          }

          return new BigInteger(ans, this.equals(y));
     }
     /**
     * Sees if two arrays are valid for subtraction by checking if all of the numbers on the top are bigger than all the numbers on the bottom.
     * @param xNums The top array.
     * @param yNums The bottom array.
     */
     public boolean validToSubtract(int[] xNums, int[] yNums) {
          for (int i = 0; i < xNums.length; i++) {
               if (yNums[i] > xNums[i]) {
                    return false;
               }
          }
          return true;
     }
     /**
     * Compares two BigIntegers and returns true if this is less than the BigInteger it is being compared to.
     * @param a The BigInteger that this is being compared to.
     */
     public boolean lessThan(BigInteger a) {
          if (a.getValue().length() < this.getValue().length()) {
               return false;
          } else if (a.getValue().length() > this.getValue().length()) {
               return true;
          } else {
               for (int x = 0; x < a.toString().length(); x++) {
                    if (Character.getNumericValue(a.toString().charAt(x)) > Character.getNumericValue(this.toString().charAt(x))) {
                         return true;
                    } else if (Character.getNumericValue(a.toString().charAt(x)) < Character.getNumericValue(this.toString().charAt(x))) {
                         return false;
                    }
               }
          }
          return false;
     }
     /**
     * Multiplies two BigIntegers and returns the result.
     * @param operand The BigInteger to be multiplied.
     */
     public BigInteger multiply(BigInteger operand) {
          BigInteger x = this;
          BigInteger y = operand;
          if (y.isNegative()) {
               y = y.negate();
          }
          if (x.isNegative()) {
               x = x.negate();
          }
          BigInteger answer = ZERO;

          while ( ! y.lessThan(new BigInteger("2"))) {
               if ( ! y.isEven()) {
                    answer = answer.add(x);
               }
               x = doubled(x);
               y = halved(y);
          }
          if ( ! y.isEven()) {
               answer = answer.add(x);
          }

          if ((this.isNegative() && ! operand.isNegative()) || ( ! this.isNegative() && operand.isNegative())) {
               return answer.negate();
          }
          return answer;
     }
     /**
     * Divides a BigInteger in two and returns the result.
     * @param a The BigInteger to be halved.
     */
     public static BigInteger halved (BigInteger a) {
          if (a.getValue().length() < 2) {
               return new BigInteger("" + Integer.parseInt(a.getValue()) / 2);
          }
          String value = "0" + a.getValue();
          String answer = "";
          for (int x = 0; x < (value.length() - 1); x++) {
               int first = Character.getNumericValue(value.charAt(x));
               int second = Character.getNumericValue(value.charAt(x + 1));
               if (first % 2 == 0) {
                    answer = answer + (second / 2);
               } else {
                    answer = answer + ((second / 2) + 5);
               }
          }
          while (answer.charAt(0) == '0') {
               answer = answer.substring(1, answer.length());
          }
          if (a.isNegative()) {
               return new BigInteger("-" + answer);
          }
          return new BigInteger(answer);
     }
     /**
     * Returns whether or not a BigInteger is even by checking the last digit.
     */
     public boolean isEven () {
          return (Integer.parseInt(this.value.substring(this.value.length() - 1, this.value.length())) % 2 == 0);
     }
     /**
     * Doubles a BigInteger and returns the result.
     * @param a The BigInteger to be doubled.
     */
     public static BigInteger doubled (BigInteger a) {
          return a.add(a);
     }
     /**
     * Divides one BigInteger by another and returns the result.
     * @param operand The BigInteger that this is being divided by.
     */
     public BigInteger divide(BigInteger operand) {
          if (operand.getValue().equals("0")) {
               throw new IllegalArgumentException("Cannot divide by zero.");
          }
          if (this.equals(operand)) {
               return ONE;
          }

          BigInteger bigger = this.absoluteValue();
          BigInteger smaller = operand.absoluteValue();
          if ( ! smaller.absoluteValue().lessThan(bigger.absoluteValue()) && ! smaller.absoluteValue().equals(bigger.absoluteValue())) {
               return ZERO;
          }
          BigInteger dividend = smaller;
          BigInteger quotient = ONE;

          while (dividend.multiply(TEN).lessThan(bigger) || dividend.equals(bigger)) {
               quotient = quotient.multiply(TEN);
               dividend = dividend.multiply(TEN);
          }

          quotient = quotient.add(bigger.subtract(dividend).divide(smaller));

          if ((this.isNegative() && ! operand.isNegative()) || ( ! this.isNegative() && operand.isNegative())) {
               return quotient.negate();
          }
          return quotient;
     }
     /**
     * Returns the remainder of one BigInteger divided by another.
     * @param operand The BigInteger that this is being divided by in order to return a remainder.
     */
     public BigInteger remainder(BigInteger operand) {
          BigInteger x = this.absoluteValue();
          BigInteger y = operand.absoluteValue();
          BigInteger remainder = x.subtract(y.multiply(x.divide(y)));
          if (this.isNegative()) {
               return remainder.negate();
          }
          return remainder;
     }

     public static void testConstructors() {
          System.out.println("Testing constructors...");
          BigInteger a = new BigInteger("12381283912391239132281381238123123");
          BigInteger b = new BigInteger("999999999999");
          BigInteger c = new BigInteger("+129381823989903290490");
          BigInteger d = new BigInteger("-120380994934802489032");
          BigInteger f = new BigInteger("0000012312312300000");
          try {
               BigInteger e = new BigInteger("12397813827w1o23");
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
          try {
               BigInteger e = new BigInteger("12397813827+23");
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
          try {
               BigInteger e = new BigInteger("123978138271-23");
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
          try {
               BigInteger e = new BigInteger("apple");
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
          try {
               BigInteger e = new BigInteger("");
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
     }
     public static void testIsNegative() {
          System.out.println("Testing isNegative()...");
          BigInteger a = new BigInteger("12390812389034289304");
          BigInteger b = new BigInteger("+129381823989903290490");
          System.out.println( ! b.isNegative());
          BigInteger c = new BigInteger("-120380994934802489032");
          System.out.println(c.isNegative());
          System.out.println(c.toString().equals("-120380994934802489032"));
          BigInteger d = new BigInteger("1");
          System.out.println( ! d.isNegative());
          System.out.println(a.add(d).toString().equals("12390812389034289305"));
          System.out.println( ! ZERO.isNegative());
     }
     public static void testEquals() {
          System.out.println("Testing equals...");
          BigInteger a = new BigInteger("1000");
          BigInteger b = new BigInteger("1000");
          BigInteger c = new BigInteger("+1000");
          BigInteger d = new BigInteger("1001");
          BigInteger e = new BigInteger("-1000");
          System.out.println(a.equals(b));
          System.out.println(a.equals(c));
          System.out.println( ! a.equals(d));
          System.out.println( ! c.equals(e));
          String f = "apple";
          System.out.println( ! a.equals(f));
          String g = "1000";
          System.out.println( ! a.equals(g));
          BigInteger h = new BigInteger("01000");
          BigInteger i = new BigInteger("       1000       ");
          System.out.println(a.equals(h));
          System.out.println(h.equals(i));
          BigInteger j = new BigInteger("000000");
          BigInteger k = new BigInteger("0");
          System.out.println(j.equals(k));
          BigInteger l = new BigInteger("-100");
          BigInteger m = new BigInteger("-0000100");
          System.out.println(l.equals(m));
     }
     public static void testAdd() {
          System.out.println("Testing add...");
          BigInteger a = new BigInteger("10000000000000000");
          BigInteger b = new BigInteger("20000000000000000");
          BigInteger c = new BigInteger("30000000000000000");
          System.out.println(a.add(b).equals(c));
          System.out.println(a.add(a).equals(b));
          BigInteger d = new BigInteger("1293182375838747843");
          BigInteger e = new BigInteger("9328746312381328121");
          System.out.println(d.add(e).toString().equals("10621928688220075964"));
          BigInteger f = new BigInteger("100006");
          BigInteger g = new BigInteger("2004");
          System.out.println(f.add(g).toString().equals("102010"));
          BigInteger h = new BigInteger("-6000");
          BigInteger i = new BigInteger("-203");
          System.out.println(h.add(i).toString().equals("-6203"));
          BigInteger j = ZERO;
          System.out.println(g.add(j).equals(g));
          System.out.println(j.add(j).equals(ZERO));
          BigInteger k = new BigInteger("1000");
          System.out.println(h.add(k).equals(new BigInteger("-5000")));
          System.out.println(k.add(h).equals(new BigInteger("-5000")));
          System.out.println(h.add(j).equals(h));
          BigInteger l = new BigInteger("909090900000909090009");
          BigInteger m = new BigInteger("987654987654");
          System.out.println(l.add(m).equals(new BigInteger("909090900988564077663")));
          BigInteger n = new BigInteger("2");
          BigInteger o = new BigInteger("-2");
          System.out.println(n.add(o).equals(ZERO));
          System.out.println(o.add(n).equals(ZERO));
          System.out.println(o.add(o).equals(new BigInteger("-4")));
     }
     public static void testSubtract() {
          System.out.println("Testing subtract...");
          BigInteger a = new BigInteger("1234");
          BigInteger b = new BigInteger("1234");
          BigInteger c = new BigInteger("0");
          System.out.println(a.subtract(b).equals(c));
          BigInteger d = new BigInteger("-2");
          System.out.println(a.subtract(d).equals(new BigInteger("1236")));
          BigInteger e = new BigInteger("5493024");
          BigInteger f = new BigInteger("100569");
          System.out.println(e.subtract(f).equals(new BigInteger("5392455")));
          BigInteger g = new BigInteger("1000");
          System.out.println(e.subtract(g).equals(new BigInteger("5492024")));
          BigInteger h = new BigInteger("-1000");
          System.out.println(h.subtract(g).equals(new BigInteger("-2000")));
          BigInteger i = new BigInteger("-206");
          System.out.println(h.subtract(i).equals(new BigInteger("-794")));
          BigInteger j = new BigInteger("1000");
          BigInteger k = new BigInteger("206");
          System.out.println(j.subtract(k).equals(new BigInteger("794")));
          BigInteger l = new BigInteger("506");
          BigInteger m = new BigInteger("209");
          System.out.println(l.subtract(m).equals(new BigInteger("297")));
          System.out.println(k.subtract(j).equals(new BigInteger("-794")));
          BigInteger n = new BigInteger("2");
          BigInteger o = new BigInteger("-2");
          System.out.println(n.subtract(o).equals(new BigInteger("4")));
          System.out.println(o.subtract(n).equals(new BigInteger("-4")));
          System.out.println(o.subtract(o).equals(ZERO));
     }
     public static void testMultiply() {
          System.out.println("Testing multiply...");
          BigInteger a = new BigInteger("57");
          BigInteger b = new BigInteger("86");
          System.out.println(a.multiply(b).equals(new BigInteger("4902")));
          BigInteger c = new BigInteger("5000");
          BigInteger d = new BigInteger("102301023");
          System.out.println(c.multiply(d).equals(d.multiply(c)));
          System.out.println(c.multiply(ZERO).equals(ZERO));
          System.out.println(c.multiply(d).equals(new BigInteger("511505115000")));
          BigInteger e = new BigInteger("1238123");
          BigInteger f = new BigInteger("919239129391239");
          System.out.println(e.multiply(f).equals(new BigInteger("1138131108599269004397")));
          BigInteger g = new BigInteger("-6");
          BigInteger h = new BigInteger("20");
          System.out.println(g.multiply(h).equals(new BigInteger("-120")));
          System.out.println(g.multiply(h).equals(h.multiply(g)));
          BigInteger i = new BigInteger("-20");
          System.out.println(g.multiply(i).equals(new BigInteger("120")));
          BigInteger j = new BigInteger("1923923912312390230489324");
          BigInteger k = new BigInteger("523192391231923123");
          System.out.println(j.multiply(k).equals(new BigInteger("1006582352230996225772618217383457840238852")));
     }
     public static void testHalvedAndDoubled() {
          System.out.println("Testing halved and doubled...");
          BigInteger a = new BigInteger("20");
          BigInteger b = new BigInteger("85");
          BigInteger c = new BigInteger("126");
          System.out.println(halved(a).equals(new BigInteger("10")));
          System.out.println(halved(b).equals(new BigInteger("42")));
          System.out.println(halved(c).equals(new BigInteger("63")));
          BigInteger d = new BigInteger("-100");
          System.out.println(halved(d).equals(new BigInteger("-50")));
          System.out.println(halved(ZERO).equals(ZERO));
          System.out.println(doubled(a).equals(new BigInteger("40")));
          System.out.println(doubled(b).equals(new BigInteger("170")));
          System.out.println(doubled(c).equals(new BigInteger("252")));
          System.out.println(doubled(ZERO).equals(ZERO));
     }
     public static void testDivide() {
          System.out.println("Testing divide...");
          BigInteger a = new BigInteger("15");
          BigInteger b = new BigInteger("5");
          System.out.println(a.divide(b).equals(new BigInteger("3")));
          BigInteger c = new BigInteger("2");
          System.out.println(a.divide(c).equals(new BigInteger("7")));
          BigInteger d = new BigInteger("2000");
          System.out.println(d.divide(c).equals(new BigInteger("1000")));
          System.out.println(d.divide(d).equals(ONE));
          System.out.println(b.divide(a).equals(ZERO));
          BigInteger f = new BigInteger("-5");
          System.out.println(a.divide(f).equals(new BigInteger("-3")));
          System.out.println(f.divide(a).equals(ZERO));
          System.out.println(ONE.negate().divide(ONE).equals(ONE.negate()));
          System.out.println(d.divide(d).equals(ONE));
          BigInteger g = new BigInteger("1923512356");
          BigInteger h = new BigInteger("1239123");
          System.out.println(g.divide(h).equals(new BigInteger("1552")));
          BigInteger i = new BigInteger("81213912391923912391923132");
          BigInteger j = new BigInteger("1236123818744");
          System.out.println(i.divide(j).equals(new BigInteger("65700467186566")));
          try {
               BigInteger e = d.divide(ZERO);
          } catch (IllegalArgumentException e) {
               System.out.println("true");
          }
     }
     public static void testRemainder() {
          System.out.println("Testing remainder...");
          BigInteger a = new BigInteger("100");
          BigInteger b = new BigInteger("99");
          System.out.println(a.remainder(b).equals(ONE));
          BigInteger c = new BigInteger("5");
          System.out.println(a.remainder(c).equals(ZERO));
          BigInteger d = new BigInteger("3");
          System.out.println(a.remainder(d).equals(ONE));
          BigInteger e = new BigInteger("18");
          BigInteger f = new BigInteger("4");
          BigInteger g = new BigInteger("-18");
          BigInteger h = new BigInteger("-4");
          System.out.println(e.remainder(f).equals(new BigInteger("2")));
          System.out.println(g.remainder(f).equals(new BigInteger("-2")));
          System.out.println(g.remainder(h).equals(new BigInteger("-2")));
          BigInteger i = new BigInteger("19239123912399123");
          BigInteger j = new BigInteger("123");
          System.out.println(i.remainder(j).equals(new BigInteger("63")));
     }
}
