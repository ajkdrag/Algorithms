/*

  Problem at : https://projecteuler.net/problem=29
  
*/

public class Problem29 {
	public static void main(String args[]) {
		Solution.solve(2, 100, 2, 100);
	}
}

class Solution29 {
	static int[][] dp;
	static int count = 0;

	static void solve(int a_start, int a_end, int b_start, int b_end) {
		filldp(a_start, a_end, b_start, b_end);
		System.out.println(count);
	}

	static void filldp(int a_start, int a_end, int b_start, int b_end) {
		int largest_exp = (int)(Math.log(a_end) / Math.log(2));
		dp = new int[a_end + 1][b_end * largest_exp + 1];
		for (int a = a_start; a <= a_end; ++a) {

			int base = a;
			int exp = 1;

			for (int i = 2; i * i <= a; ++i) {
				int pow = get_power(i, base);
				if (pow != -1) {
					base = i;
					exp = pow;
					break;
				}
			}

			for (int b = b_start; b <= b_end; ++b) {
				int e = b * exp;
				if (dp[base][e] == 0) {
					dp[base][e] = 1;
					dp[a][b] = 1; ++count;

				}
			}
		}
	}

	static int get_power(int x, int y) {
		int pow = 0;
		if (y == 1) return 0;
		while (x <= y && y % x == 0) {
			y /= x; ++pow;
		}
		return y == 1 ? pow: -1;
	}
}

