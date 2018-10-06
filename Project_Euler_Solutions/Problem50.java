/*

  Problem at : https://projecteuler.net/problem=50

*/

import java.util.ArrayList;
public class Problem50 {
    public static void main(String args[]) {
        System.out.println(Solution50.solve(1000000));
    }
}

class Solution50 {
    static ArrayList<Long> pref;
    static int[] sieve;
    static int pointer;
    static int LIMIT;
    
    static int solve(int n){
        fillSieve(n);
        return getRes();
    }
    
   
   static int getRes(){
       int max = 0;
       int len = 0;
       for(int i = pointer; i>0; i--){
           if(i <= len) break;
           for(int j = 0; j < i; ++j){
                int to_test = (int)(pref.get(i) - pref.get(j));
                if ((to_test & 1) == 1 &&(sieve[to_test >> 6] & (1 << ((to_test & 63) >> 1))) != 0){
                    int t_len = i - j;
                    if(t_len > len){
                        len = t_len;
                        max = to_test;
                    }
                }
           }
       }
       return max;
   }
    
    static void fillSieve(int limit) {
        LIMIT = limit;
        int len = (limit>>6) + 1;
		sieve = new int[len];
		pref = new ArrayList<Long>(limit);
		for (int i = 0; i < len; i++) {
			sieve[i] = 0xffffffff;
		}
        
        pref.add(0L);
        pref.add(2L);
        int top = 1;
        pointer = 1;
		sieve[0] &= ~1;

		int i = 3;
		for (; i <= limit; i += 2) {
			if ((sieve[i >> 6] & (1 << ((i & 63) >> 1))) != 0) {
			    long cumSum = i + pref.get(top);
			    top++;
			    pref.add(cumSum);
			    if(cumSum <= limit) pointer = top;
			    if(i > Math.sqrt(limit)) continue;
				for (int j = i*i; j <= limit; j += i << 1) {
					sieve[(int)j >> 6] &= ~(1 << (((int)j & 63) >> 1));
				}
			}
		}
		pref.trimToSize();
	}
}

