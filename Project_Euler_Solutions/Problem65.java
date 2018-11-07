/*

  Problem at : https://projecteuler.net/problem=65

*/

import java.math.BigInteger;

public class Problem65 {
    public static void main(String args[]) {
        long start = System.nanoTime();
        System.out.println(Solution65.solve());
    }
}

class Solution65 {
    static int solve(){
        BigInteger p_1 = BigInteger.valueOf(8);
        BigInteger p_0 = BigInteger.valueOf(3);
        BigInteger p_i,p_j;
        
        for(int i = 2 ; i <= 33; ++i){
            BigInteger a = BigInteger.valueOf(i<<1);
            p_j = p_0.multiply(BigInteger.ONE.add(a)).add(p_1.multiply(BigInteger.ONE.add(a.shiftLeft(1))));
            p_i = p_0.add(p_1.shiftLeft(1));
            p_0 = p_i;
            p_1 = p_j;
        }
        
        int sum_n = 0;
        String numer = p_1.add(p_0).toString();
        for(int i = numer.length() - 1; i >= 0; --i) {
            int digit = numer.charAt(i) - '0';
            sum_n += digit;
        }
        
        return sum_n;
    }
}


