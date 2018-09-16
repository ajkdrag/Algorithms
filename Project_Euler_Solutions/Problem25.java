/*

  Problem 25 : https://projecteuler.net/problem=25
  
*/

import java.math.BigInteger;
public class Problem25 {
    public static void main(String args[]) {
        Solution25 sol = new Solution25();
        System.out.println(sol.solve(1000));
    }
}

class Solution25 {
    int solve(int d){
        int i = 1;
        int n = 2;
        BigInteger limit = (new BigInteger("10")).pow(d-1);
        BigInteger[] fib = new BigInteger[2];
    
        fib[0] = BigInteger.ONE;
        fib[1] = BigInteger.ONE;
    
        while ((fib[i]).compareTo(limit) < 0) {
            i = (i + 1) & 1;
            ++n;
            fib[i] = fib[i].add(fib[(i + 1) & 1]);
        }
        return n;
    }
}

