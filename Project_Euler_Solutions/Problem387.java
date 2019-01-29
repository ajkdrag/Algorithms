/*
  Problem at : https://projecteuler.net/problem=387
*/

import java.math.BigInteger;

public class Problem387 {
	
	static final long LIMIT = 100000000000000L;
    static BigInteger sum = BigInteger.ZERO;
	
	public static void main(String[] args) {
	       System.out.println(solve());
	}
	
	public static String solve() {
		for (int i = 1; i <= 9; i++) 
			getHarshadPrimes(i, i, false);
		return sum.toString();
	}
	
	static void getHarshadPrimes(long n, int digitSum, boolean isStrong) {
		long m = n * 10;
		int s = digitSum;
		for (int i = 0; i < 10 && m < LIMIT; i++, m++, s++) {
			if (isStrong && isPrime(m))
				sum = sum.add(BigInteger.valueOf(m));
			if (m % s == 0)
				getHarshadPrimes(m, s, isPrime(m / s));
		}
	}
	
	static boolean isPrime(long n){
        if (n == 3)
            return true;
        if ((n&1) == 0)
            return false;
        if (n % 3 == 0)
            return false;
        long i = 5;
        long w = 2;

        while(i * i <= n){
            if (n % i == 0)
                return false;
            i += w;
            w = 6 - w;
        }
        return true;
	}
}


