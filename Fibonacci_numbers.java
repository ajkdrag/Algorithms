/*
  Problem at : https://www.spoj.com/problems/MAIN74/
*/

import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Solution sol = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int t = Integer.parseInt(line);
        while(t-- > 0){
            long n = Long.parseLong(br.readLine());
            System.out.println(sol.solve(n+1));    
        }
    }
}

class Solution {
    private static final long MOD = 1000000007; 
    //int[] dp = new int[2 + MOD>>2];
    long a = -1;
    long b = -1;
    long c = -1;
    long d = -1;
    
    int solve(long n){
        if(n < 3)
            return (int)((n-1)<<1);
        fib(n);
        return (int)((a+b)%MOD);
    }
    
    void fib (long n) {
        if (n == 0){
            a = 0;
            b = 1;
            return;
        }

        fib(n >> 1);
        c = mulMod(a, - a + b*2);
        d = (mulMod(a,a) + mulMod(b,b))%MOD;
        if ((n & 1) == 1){
            a = d;
            b = (c + d)%MOD;
            //System.out.println(a + ", " + b);
            return;
        }
        a = c;
        b = d;
        //System.out.println(a + ", " + b);
        return;
    }
    
    long mulMod(long num, long times) {
      if(times < 0)
          times += MOD;
      long temp = num;
      long res = 0;
      while (times > 0) {
        if ((times & 1) == 1)
          res = (res + temp) % MOD;

        temp = (temp << 1) % MOD;

        times >>= 1;
      }

      return res;
    }
}

