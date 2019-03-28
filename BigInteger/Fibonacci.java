public class Fibonacci {
     public static void main(String[] args) {
		if (args.length == 0 || args.length > 1) {
               throw new IllegalArgumentException("Invalid input!");
     	} else {
			try {
				new BigInteger(args[0]);
				if (Integer.parseInt(args[0]) < 0) {
					throw new IllegalArgumentException("Invalid input!");
				}
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException("Invalid input!");
			}
		}
		System.out.println(fibonacciAt(new BigInteger(args[0])));
     }
	public static BigInteger fibonacciAt(BigInteger n) {
		BigInteger a = new BigInteger("0");
		BigInteger b = new BigInteger("1");
		BigInteger temp = new BigInteger("0");

		while ( ! n.lessThan(new BigInteger("0")) || n.equals(new BigInteger("0"))) {
			temp = a;
			a = a.add(b);
			b = temp;
			n = n.subtract(new BigInteger("1"));
		}

		return b;
	}
}
