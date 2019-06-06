/*
  Problem at : https://www.spoj.com/problems/FIBOSUM/
*/

import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Solution sol = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int t = Integer.parseInt(line);
        while(t-- > 0){
            String[] ip = br.readLine().split(" ");
            sol.solve(Integer.parseInt(ip[0]), Integer.parseInt(ip[1]));    
        }
    }
}

class Solution {
    long a = 1;
    long b = 0;
    long c = -1;
    long d = -1;
    private static final int MOD = 1000000007;
    
    void solve(int n, int m){
        long res = 0;
        cumFib(m);
        res = a%MOD;
        if(n >=2){
            cumFib(n-1);
            res = (res - a + MOD)%MOD;
        }
        System.out.println(res);
    }
    
    void cumFib(int n){
        if(n < 2){
            a = n;
            b = n - 1;
            return;
        }
        cumFib((1+n)>>1);
        c = mulMod(a<<1, b) + (a<<1) - mulMod(b,b);
        d = mulMod(a - b,a - b) + mulMod(b + 1,b + 1) - 1;
        if(c < 0)
            c += MOD;
        if(d < 0)
            d += MOD;
        if((n&1) == 1){
            a = d%MOD;
            b = (c - d - 1)%MOD;
            return;
        }
        a = c%MOD;
        b = d%MOD;
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


