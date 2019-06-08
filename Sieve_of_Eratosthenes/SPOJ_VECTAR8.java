/*
  Problem at : https://www.spoj.com/problems/VECTAR8/
*/

import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Solution sol = new Solution();
        sol.fillSieve();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            System.out.println(sol.solve(Integer.parseInt(br.readLine())));
        }
    }
}

class Solution {
    int[] sieve;
    int[] cum;
    int limit = 1000000;
    
    void fillSieve(){
        sieve = new int[(limit + 1)>>1];
        cum = new int[(limit + 1)>>1];
        sieve[0] = 1;
        cum[0] = 1;
        for(long i = 3; i < limit; i += 2){
            if(sieve[(int)i>>1] == 0){
                if(satisfies((int)i)){
                    cum[(int)i>>1] = 1;
                }
                for(long j = i*i; j < limit; j += (i<<1)){
                    sieve[(int)j>>1] = 1;
                }
            }
            cum[(int)i>>1] += cum[((int)i>>1)-1];
        }
        
    }
    
    boolean satisfies(int i){
        if(i < 10)
            return true;
        int d = numDigits(i);
        i%=d;
        if(i == 1 || (i&1)==0 || i*10 < d)
            return false;
        if(cum[i>>1] != cum[(i>>1) - 1])
            return true;
        return false;
    }
    
    int numDigits(int i){
        int n = 1;
        if ( i >= 100000000 ) { n *= 100000000; i /= 100000000; }
        if ( i >= 10000     ) { n *= 10000; i /= 10000; }
        if ( i >= 100       ) { n *= 100; i /= 100; }
        if ( i >= 10        ) { n *= 10; }
        return n;
    }
    
    int solve(int n){
        if(n < 4)
            return n - 1;
        return cum[(n-1)>>1];
    }
}


