/*

  Problem at : https://projecteuler.net/problem=69

*/

public class Problem69 {
    public static void main(String args[]) {
        System.out.println(Solution69.solve(1000001));
    }
}

class Solution69 {
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
        
        long n = 6;
        long phi = 2;
        for(long k = 10; k < limit; k += 4){
            long phi_k = phiSieve[(int)k];
            if(k*phi >= n*phi_k){
                n = k;
                phi = phi_k;
            }
        }
        return (int)n;
    }
}


