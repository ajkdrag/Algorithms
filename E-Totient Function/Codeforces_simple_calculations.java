/*
  Problem at : http://codeforces.com/gym/100975/problem/F
  Need to find (2^(2^n) + 1) mod k for given 'n' and 'k' 
  Application of Euler's Theorem and Euler's Totient function.
  Refer to the congruence : x^n ≡ x^(φ(m)+[n mod φ(m)]) mod m. (n >= log2(m))
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;

public class MyClass {

	public static void main(String args[]) throws java.lang.Exception {
		Reader reader = new FileReader("calc.in");
		Writer writer = new FileWriter("calc.out");
		StreamTokenizer in =new StreamTokenizer(new BufferedReader(reader));
		PrintWriter out = new PrintWriter(writer);

		Solution_Simple_Calculations.fillPhiSieve();

		while ( in .nextToken() != StreamTokenizer.TT_EOF) {
			int n = (int) in .nval; in .nextToken();
			int k = (int) in .nval;
			int res = Solution_Simple_Calculations.solve(n, k);
			out.println(res);

		}
		out.flush();
		out.close();
	}
}

class Solution_Simple_Calculations {
	static int[] phiSieve;

	static int solve(int n, int k) {
    // Since 'n' needs to be >= log2(k), max value for 'k' is 1000000 and log2(k) is less compared to 30 anyway.
		if (n >= 30) {
			int phi = phiSieve[k];
      // find 2^n % φ(k)
			int two_exp_n = fastExpMod(2, n, phi);
      // find (2^ (φ(k) + 2^n % φ(k)) + 1)% k
			int two_exp_two_exp_n = fastExpMod(2, (phi + two_exp_n), k);
			return (two_exp_two_exp_n + 1) % k;
		}
		else {
      //  use simple modular exponentiation
			int two_exp_n = 1 << n;
			int two_exp_two_exp_n = fastExpMod(2, two_exp_n, k);
			return (two_exp_two_exp_n + 1) % k;
		}
	}

  // Totient sieve
	static void fillPhiSieve() {
		phiSieve = new int[1000031];
		phiSieve[1] = 1;
		for (int i = 2; i < 1000031; ++i) {
			if (phiSieve[i] == 0) {
				phiSieve[i] = i - 1;
				for (int j = i * 2; j < 1000031; j += i) {
					if (phiSieve[j] == 0) phiSieve[j] = j;
					phiSieve[j] -= phiSieve[j] / i;
				}
			}
		}
	}
  
  // binary exponentiation with mod
	static int fastExpMod(int base, int pow, int mod) {
		long res = 1;
		long curr = base;
		while (pow > 0) {
			if ((pow & 1) == 1) res = (res * curr) % mod;
			curr = (curr * curr) % mod;
			pow >>= 1;
		}
		return (int) res;
	}
}
