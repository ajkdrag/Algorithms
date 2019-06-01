/*
  Problem at : http://codeforces.com/gym/100963/problem/J
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException{
        Solution sol = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        while((line = br.readLine()) != null){
            String[] ip = line.split(" ");
            long n = Long.parseLong(ip[0]);
            long m = Long.parseLong(ip[1]);
            long a = Long.parseLong(ip[2]);
            long k = Long.parseLong(ip[3]);
            if((n + m + a + k) == 0)
                break;
            sol.solution(n, m, a, k);
        }
    }
}

class Solution {
    long x, y;
   
    void solution(long n, long m, long a, long k){
        long res = -1;
            res = solve(n, m, a, k);
        
        if(res == -1)
            System.out.println("Impossible");
        else
            System.out.println(res);
    }

    // assume (k - n) is positive
    long solve(long n, long m, long p, long q){
        long a = m;
        long b = p;
        long c = q - n;
        long sign = 1;
        if(c < 0)
            sign = -1;
        long d = eed(a, b);
        c *= sign;
        y *= -sign;
        x *= sign;
 
        if((c % d) != 0)
            return -1;
        else {
            long l = 0;
            long u = 0;
            if(x >= 0){
                l = (x*c)/b;
            }
            else
                l = -(-x*c + b - 1)/b;
            
            if(y >= 0){
                u = y*c/a;
                if(((y*c)%a) == 0)
                    --u;
            }
            else {
                u = -(-y*c + a)/a;
            }
            
            long k = u < l ? u : l;
            y = y*(c/d) -  k*(a/d);
            return q + p*y;
        }
        
    }
    
    long eed(long a, long b){
        if (b == 0) {
            x = 1;
            y = 0;
            return a;
        }
        long temp;
        long d = eed(b, a%b);
        
        temp = x;
        x = y;
        y = temp - (a/b)*y;

        return d;
    }
}


