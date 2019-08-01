/*
  Problem at : https://codeforces.com/contest/848/problem/A
*/

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException{
        Solution sol = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(sol.solve(n));
    }
}

class Solution {
    String solve(int x) {
		if(x == 0) {
			return "a";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 26; i++) {
			if (x == 0) {
				break;
			}
			int cur = 0;
			while (cur * (cur - 1) / 2 <= x) {
				cur++;
			}
			--cur;
			x -= cur * (cur - 1) / 2;
			char c = (char)('a' + i);
			for (int z = 0; z < cur; z++) {
				sb.append(c);
			}
		}
		return sb.toString();
	}    
}

