/*
  Problem 27 : https://projecteuler.net/problem=27
*/

public class Problem27 {
	public static void main(String args[]) {
		System.out.println(Solution.solve(1000));
	}
}

class Solution27 {
	static int[] sieve;
	static int limit;

	static int solve(int l) {
		limit = l;
		fillSieve(limit);
		int bMax = 0,
		aMax = 0,
		nMax = 0,
		pMax = 0;
		for (int b = limit; b >= 1; b--) {
			if (b < nMax) break;
			if (sieve[b] == 1) continue;
			for (int a = 2; a <= limit; a += 2) {
				int count = 0;
				int p = b;
				for (int n = a;; n += 2) {
					p = p + n;
					if (p <= limit && sieve[p] == 0)++count;
					else {
						p = p - n;
						break;
					}
				}

				if (count > nMax) {
					nMax = count;
					bMax = b;
					aMax = a - 1;
					pMax = p;
				}
			}
		}

		int k = getK(aMax, bMax, pMax);
		int newA = (aMax - (k << 1));
		int newB = pMax;
		return newA * newB;
	}

	static int getK(int a, int b, int p) {
		int temp = a + (int) Math.sqrt(a * a - (b - p) << 2);
		return temp >> 1;
	}

	static void fillSieve(int limit) {
		sieve = new int[limit + 1];
		sieve[0] = 1;
		sieve[1] = 1;

		for (int i = 2; i * i <= limit; ++i) {
			if (sieve[i] == 1) continue;
			for (int j = i * i; j <= limit; j += i) {
				sieve[j] = 1;
			}
		}
	}
}
