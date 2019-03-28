public class Tuple {
     private int k;
     private int[] data;
     /**
     * Contains tests for the methods and constructors of the Tuple Class
     * @param args An array of strings that is passed in when the program is run
     */
     public static void main (String[] args) {
          testEquals();
          testConstructors();
          testMakeTupleFromData();
          testAdd();
          testClone();
          testLength();
          testSum();
          testToString();
     }
     /**
     * Constructs a new k-tuple with all of the elements set to zero.
     * @param k indicates the number of elements in the tuple.
     */
     public Tuple (int k) {
          if (k < 0) {
               throw new IllegalArgumentException("K cannot be negative!");
          }
          this.k = k;
          data = new int[k];
          for (int i = 0; i < k; i++) {
               data[i] = 0;
          }
     }
     /**
     * Constructs a new tuple from array data, k becomes the length of the data.
     * @param data An array of integers that becomes the data of the tuple.
     */
     public Tuple (int[] data) {
          this.k = data.length;
          this.data = data;
     }
     /**
     * Returns a tuple that is constructed from array data.
     * @param data An array of integers that becomes the data of the tuple.
     */
     public static Tuple makeTupleFromData(int[] data) {
          return new Tuple(data);
     }
     /**
     * Returns a new tuple whose elements are the sums of the respective elements of this tuple and tuple t.
     * Throws an error IllegalArgumentException if the tuples are not the same length.
     * @param t A tuple that is being added to this tuple.
     */
     public Tuple add(Tuple t) {
          if (this.k != t.length()) {
               throw new IllegalArgumentException("Lengths do not match!");
          }
          int[] sumData = new int[t.length()];
          for (int i = 0; i < t.length(); i++) {
               sumData[i] = this.data[i] + t.getElement(i);
          }
          return new Tuple(sumData);
     }
     /**
     * Returns an exact copy of the tuple.
     */
     public Tuple clone() {
          return new Tuple(this.data);
     }
     /**
     * Returns true if object is a tuple which has the same length and elements, respectively, as this tuple.
     * @param obj A tuple that is being checked against this to see if they are the same.
     */
     public boolean equals(Object obj) {
          if (obj instanceof Tuple) {
               Tuple tuple = (Tuple)obj;
               if (tuple.length() == this.k) {
                    for (int i = 0; i < this.k; i++) {
                         if (tuple.getElement(i) != this.data[i]) {
                              return false;
                         }
                    }
                    return true;
               }  else {
                    return false;
               }
          } else {
               return false;
          }
     }
     /**
     * Returns the value of the ith element of this tuple (zero-based indexing).
     * @param i The index of the element that is to be returned.
     */
     public int getElement (int i) {
          if (i >= this.k || i < 0) {
               throw new IllegalArgumentException("Invalid index!");
          }
          return this.data[i];
     }
     /**
     * Sets the value of the ith element of this tuple (zero-based indexing).
     * @param i The index of the value that is to be set.
     * @param value The value that is to be set at the index.
     */
     public void setElement(int i, int value) {
          if (i >= this.k || i < 0) {
               throw new IllegalArgumentException("Invalid index!");
          }
          this.data[i] = value;
     }
     /**
     * Returns the number of elements in this tuple.
     */
     public int length() {
          return this.k;
     }
     /**
     * Returns the sum of the elements in this tuple.
     */
     public int sum() {
          int sum = 0;
          for (int i = 0; i < this.k; i++) {
               sum += this.data[i];
          }
          return sum;
     }
     /**
     * Returns the string that denotes this tuple.
     */
     public String toString() {
          String tupleString = "[";
          for (int i = 0; i < this.k; i++) {
               tupleString = tupleString + this.data[i];
               if (i != this.k - 1) {
                    tupleString = tupleString + (",");
               }
          }
          tupleString = tupleString + "]";
          return tupleString;
     }
     /**
     * Tests the equals method
     */
     public static void testEquals() {
          Tuple tuple1 = new Tuple(new int[] {1, 2, 3, 4, 5});
          Tuple tuple2 = new Tuple(new int[] {1, 2, 3, 4, 5});
          System.out.println("Testing equals method...");
          System.out.println(tuple1.equals(tuple2));
          Tuple tuple3 = new Tuple(new int[] {});
          Tuple tuple4 = new Tuple(0);
          System.out.println(tuple3.equals(tuple4));
          Tuple tuple5 = new Tuple(new int[] {0, 0, 0});
          Tuple tuple6 = new Tuple(3);
          System.out.println(tuple5.equals(tuple6));
          System.out.println( ! tuple1.equals(tuple3));
          Tuple tuple7 = new Tuple(new int[] {1, 2, 3, 4, 6});
          System.out.println( ! tuple1.equals(tuple7));
          try {
               boolean test = tuple1.equals("testing!");
          } catch(Exception e) {
               System.out.println("true");
          }
          try {
               System.out.println( ! tuple1.equals(null));
          } catch(Exception e) {
               System.out.println("true");
          }
     }
     /**
     * Tests both one-argument Tuple constructors
     */
     public static void testConstructors() {
          System.out.println("Testing Tuple constructors...");
          Tuple tuple1 = new Tuple(0);
          Tuple tuple2 = new Tuple(5);
          Tuple tuple3 = new Tuple(new int[] {});
          System.out.println(tuple1.equals(tuple3));
          Tuple tuple4 = new Tuple(new int[] {0, 0, 0, 0, 0});
          System.out.println(tuple2.equals(tuple4));
          Tuple tuple5 = new Tuple(new int[] {1, 2, 3, 4, 5});
          System.out.println(tuple5.equals(tuple5));
          System.out.println( ! tuple1.equals(tuple5));
          try {
               Tuple tuple6 = new Tuple(-4);
          } catch (Exception e) {
               System.out.println("true");
          }
     }
     /**
     * Tests the makeTupleFromData method
     */
     public static void testMakeTupleFromData() {
          System.out.println("Testing testMakeTupleFromData...");
          int[] data1 = new int[] {1, 2, 3, 4, 5};
          Tuple tuple1 = new Tuple(data1);
          System.out.println(tuple1.equals(makeTupleFromData(data1)));
          Tuple tuple2 = new Tuple(0);
          System.out.println(tuple2.equals(makeTupleFromData(new int[] {})));
          System.out.println( ! tuple1.equals(makeTupleFromData(new int[] {})));
          System.out.println( ! tuple2.equals(makeTupleFromData(data1)));
     }
     /**
     * Tests the add method
     */
     public static void testAdd() {
          System.out.println("Testing add method...");
          Tuple tuple1 = new Tuple(new int[] {1, 2, 3, 4, 5});
          Tuple tuple2 = new Tuple(new int[] {1, 2, 3, 4, 5});
          Tuple tuple3 = new Tuple(new int[] {2, 4, 6, 8, 10});
          System.out.println(tuple1.add(tuple2).equals(tuple3));
          Tuple tuple4 = new Tuple(new int[] {});
          System.out.println(tuple4.add(tuple4).equals(tuple4));
          Tuple tuple5 = new Tuple(new int[] {1});
          Tuple tuple6 = new Tuple(new int[] {2});
          Tuple tuple7 = new Tuple(new int[] {3});
          System.out.println(tuple5.add(tuple6).equals(tuple7));
          System.out.println( ! tuple7.add(tuple6).equals(tuple5));
          System.out.println( ! tuple2.add(tuple3).equals(tuple1));
          try {
               Tuple tuple8 = tuple1.add(tuple5);
          } catch(Exception e) {
               System.out.println("true");
          }
     }
     /**
     * Tests the clone method
     */
     public static void testClone() {
          System.out.println("Testing clone method...");
          Tuple tuple1 = new Tuple(new int[] {1, 2, 3, 4, 5});
          Tuple tuple2 = new Tuple(new int[] {1, 2, 3, 4, 5, 6});
          Tuple tuple3 = new Tuple(new int[] {1, 2, 3, 4, 5});
          System.out.println(tuple1.equals(tuple1.clone()));
          System.out.println(tuple2.equals(tuple2.clone()));
          System.out.println( ! tuple2.equals(tuple1.clone()));
          System.out.println(tuple1.equals(tuple3.clone()));
     }
     /**
     * Tests the getElement and setElement methods
     */
     public void testGetElementAndSetElement() {
          Tuple tuple1 = new Tuple(new int[] {1, 2, 3, 4, 5});
          System.out.println("Testing getElement and setElement...");
          System.out.println(tuple1.getElement(1) == 1);
          System.out.println(tuple1.getElement(4) == 4);
          tuple1.setElement(2, 50);
          System.out.println(tuple1.getElement(2) == 50);
          try {
               int test = tuple1.getElement(-1);
          } catch(Exception e) {
               System.out.println("true");
          }
          try {
               tuple1.setElement(20, 3);
          } catch(Exception e) {
               System.out.println("true");
          }
          try {
               int test = tuple1.getElement(20);
          } catch(Exception e) {
               System.out.println("true");
          }
          try {
               tuple1.setElement(-1, 3);
          } catch(Exception e) {
               System.out.println("true");
          }
     }
     /**
     * Tests the length method
     */
     public static void testLength() {
          System.out.println("Testing length method...");
          Tuple tuple1 = new Tuple(new int[] {1, 2, 3, 4, 5});
          System.out.println(tuple1.length() == 5);
          Tuple tuple2 = new Tuple(new int[] {});
          System.out.println(tuple2.length() == 0);
          Tuple tuple3 = new Tuple(6);
          System.out.println(tuple3.length() == 6);
     }
     /**
     * Tests the sum method
     */
     public static void testSum() {
          System.out.println("Testing sum method...");
          Tuple tuple1 = new Tuple(new int[] {1, 2, 3, 4, 5});
          System.out.println(tuple1.sum() == 15);
          Tuple tuple2 = new Tuple(new int[] {});
          System.out.println(tuple2.sum() == 0);
          Tuple tuple3 = new Tuple(20);
          System.out.println(tuple3.sum() == 0);
     }
     /**
     * Tests the toString method
     */
     public static void testToString() {
          System.out.println("Testing toString method...");
          Tuple tuple1 = new Tuple(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
          System.out.println(tuple1.toString().equals("[1,2,3,4,5,6,7,8,9,10]"));
          Tuple tuple2 = new Tuple(new int[] {});
          System.out.println(tuple2.toString().equals("[]"));
          Tuple tuple3 = new Tuple(2);
          System.out.println(tuple3.toString().equals("[0,0]"));
     }
}
