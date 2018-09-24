/*

  Problem at : https://projecteuler.net/problem=48

*/

public class Problem48 {
	public static void main(String args[]) {
		Solution48.solve(2000000, 10);
	}
}

class Solution48 {
	static int LIMIT;
	static int[] sieve;
	static long[] selfPowers;
	static long MOD;
	static long sum = 0;

	static void solve(int limit, int d) {
		LIMIT = limit;
		MOD = (long) Math.pow(10, d);
		selfPowers = new long[LIMIT + 1];
		selfPowers[1] = 1;
		selfPowers[2] = 4;
		selfPowers[4] = 256;
		sum += 261;
		fillSieve(LIMIT);
		fillSelfPowers();
		sum %= MOD;

		// Simpler, faster, memory efficient brute force solution
		// for(int i = 1; i <= LIMIT; ++i){
		//     long val = fastExpMod(i,i,MOD);
		//     sum += val;
		// }
		// sum %= MOD;
		System.out.println(sum);
	}

	static void fillSelfPowers() {
		for (int i = 6; i <= LIMIT; i += 2) {
			if (i % 10 == 0) continue;
			for (int j = (int) Math.sqrt(i); j >= 2; j--) {
				if (i % j == 0) {
					int q = i / j;
					long val = mulMod(fastExpMod(selfPowers[j], q, MOD), fastExpMod(selfPowers[q], j, MOD), MOD);
					selfPowers[i] = val;
					sum += val;
					break;
				}
			}
		}
	}

	static void fillSieve(int limit) {
		int len = (limit >> 6) + 1;
		sieve = new int[len];

		sieve[0] |= 1;

		int i = 3;
		for (; i * i <= limit; i += 2) {
			if ((sieve[i >> 6] & (1 << ((i & 63) >> 1))) == 0) {
				long val = fastExpMod(i, i, MOD);
				selfPowers[i] = val;
				sum += val;
				for (long j = i * i; j <= limit; j = j + (i << 1)) {
					int k = (int) j;
					if ((sieve[k >> 6] & (1 << ((k & 63) >> 1))) == 0) {
						val = fastExpMod(k, k, MOD);
						selfPowers[k] = val;
						sum += val;

					}
					sieve[k >> 6] |= (1 << ((k & 63) >> 1));
				}
			}
		}

		for (; i <= limit; i += 2) {
			if ((sieve[i >> 6] & (1 << ((i & 63) >> 1))) == 0) {
				long val = fastExpMod(i, i, MOD);
				selfPowers[i] = val;
				sum += val;
			}
		}
	}

	static long mulMod(long a, long b, long m) {
		if (a < 0xfffffff && b < 0xfffffff) return (a * b) % m;
		int l = 0;
		long c = m;
		while ((c & 0x8000000000000000L) == 0) {++l;
			c <<= 1;
		}
		l -= 2;
		long mask = (1 << l) - 1;
		long res = 0;
		while (b > 0) {
			res += ((b & mask) * a);
			res %= m;
			b >>= l;
			a <<= l;
			a %= m;
		}
		return res;
	}

	static long fastExpMod(long b, long exp, long m) {
		long res = 1;
		long curr = b;
		while (exp > 0) {
			if ((exp & 1) == 1) res = mulMod(res, curr, m);
			curr = mulMod(curr, curr, m);
			exp >>= 1;
		}
		return res;
	}
}

