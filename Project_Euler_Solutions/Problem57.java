/*

  Problem at : https://projecteuler.net/problem=57

*/

import java.math.BigInteger;

public class Problem57 {
    public static void main(String args[]) {
       System.out.println(Solution57.solve());
    }
}

class Solution57 {
    static int solve(){
        BigInteger p0 = BigInteger.ONE;
        BigInteger p1 = BigInteger.ONE;
        BigInteger q0 = BigInteger.ZERO;
        BigInteger q1 = BigInteger.ONE;
        
        BigInteger num = BigInteger.ZERO;
        BigInteger den = BigInteger.ZERO;
        int count = 0;
        
        for(long i = 1L; i <= 1000; ++i){
            num = p1.shiftLeft(1).add(p0);
            den = q1.shiftLeft(1).add(q0);
            if(len(num) > len(den)) ++count;
            p0 = p1;
            p1 = num;
            q0 = q1;
            q1 = den;
        }
        return count;
    }
    
    static int len(BigInteger number) {
        double factor = Math.log10(2);
        int digitCount = (int) (factor * number.bitLength() + 1);
        if (BigInteger.TEN.pow(digitCount - 1).compareTo(number) > 0) {
            return digitCount - 1;
        }
        return digitCount;
    }
}


