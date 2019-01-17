/*
  Problem at : https://projecteuler.net/problem=88
  Solution at (referring to Nayuki's repo) : https://github.com/nayuki/Project-Euler-solutions/blob/master/java/p088.java?ts=4
*/

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Problem88{
	
	public static void main(String[] args) {
		System.out.println(run());
	}

	static final int LIMIT = 12000;
	static int[] minSumProduct;

	static int run() {
		minSumProduct = new int[LIMIT + 1];
		Arrays.fill(minSumProduct, Integer.MAX_VALUE);
		for (int i = 2; i <= LIMIT * 2; i++)
			factorize(i, i, i, 0, 0);
		
		Set<Integer> items = new HashSet<>();
		int sum = 0;
		for (int i = 2; i <= LIMIT; i++){
		    int item = minSumProduct[i];
		    if(!items.contains(item)){
			    items.add(item);
			    sum += item;
		    }
			
		}
		return sum;
	}
	
	static void factorize(int n, int remain, int maxFactor, int sum, int terms) {
		if (remain == 1) {
			if (sum > n) 
				throw new AssertionError();
			
			terms += n - sum;
			if (terms <= LIMIT && n < minSumProduct[terms])
				minSumProduct[terms] = n;
			
		} else {
			for (int i = 2; i <= maxFactor; i++) {
				if (remain % i == 0) {
					int factor = i;
					factorize(n, remain / factor, Math.min(factor, maxFactor), sum + factor, terms + 1);
				}
			}
		}
	}
}


