/*

  Problem at : https://projecteuler.net/problem=70

*/

public class Problem70 {
    public static void main(String args[]) {
        System.out.println(Solution69.solve(10000001));
    }
}

class Solution70{
    static int[] phiSieve;
    static int solve(int limit){
        phiSieve = new int[limit];
        phiSieve[1] = 1;
        for(int i = 2; i <= limit>>1; ++i){
            if(phiSieve[i] == 0){
                for(int j = i<<1; j < limit; j+=i){
                    int val = phiSieve[j];
                    if(val == 0) val = j;
                    val -= val/i;
                    phiSieve[j] = val;
                }
            }
        }
        
        long n = 87109;
        long phi = 79180;
        for(long k = 10; k < limit; ++k){
            long phi_k = phiSieve[(int)k];
            if(k*phi <= n*phi_k){
                if(get_digitCount(phi_k) != get_digitCount(k)) continue;
                n = k;
                phi = phi_k;
            }
        }
        return (int)n;
    }
    
    static long get_digitCount(long cube){
        long res = 0L;
        while(cube > 0){
            long r = cube%10;
            res += (1L<<(r<<2));
            cube /= 10L;
        }
        return res;
    }
}


