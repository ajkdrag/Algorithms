/*
  Problem 23 : https://projecteuler.net/problem=23
*/

import java.util.HashSet;
import java.util.ArrayList;

public class Problem23 {

	public static void main(String[] args) {
		int n = 28123;

		ArrayList < Integer > abNums = new ArrayList < Integer > ();
		HashSet < Integer > abSums = new HashSet < Integer > ();
		int[] nums = new int[n];

		int total = (n - 1);
		if ((n & 1) == 1) total = (total >> 1) * n;
		else total = (n >> 1) * total;

		for (int i = 2; i * i <= n; i++) {
			for (int j = i * i; j < n; j += i) {
				int val = nums[j];
				if (val + 1 > j) continue;
				nums[j] = val + i + (j / i);
				if (i * i == j) nums[j] -= i;
				if (nums[j] + 1 > j) abNums.add(j);
			}
		}

		for (int i = abNums.size() - 1; i >= 0; --i) {
			for (int j = i; j >= 0; --j) {
				int sum = abNums.get(i) + abNums.get(j);
				if (sum < n && !abSums.contains(sum)) {
					total -= sum;
					abSums.add(sum);
				}
			}
		}

		System.out.println(total);
	}
}
