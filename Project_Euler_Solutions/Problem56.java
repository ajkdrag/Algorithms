/*

  Problem at : https://projecteuler.net/problem=56

*/

import java.math.BigInteger;

public class Problem56 {
    public static void main(String args[]) {
        System.out.println(Solution56.solve());
    }
}

class Solution56 {
    static BigInteger bi;
    
    static int solve(){
        int max = 0;
        for(int b = 99; b >= 2; --b){
            if(9*length(99,b) <= max) break;
            for(int a = 99; a >= 2; --a){
                if(a%10 == 0) continue;
                int sod = getSOD(a,b);
                if(sod > max) max = sod;
            }
        }
        
        return max;
    }
    
    static int length(int a, int b){
        int len = 1 + (int)(Math.log10(a)*b);
        return len;
    }
    
    static int getSOD(int a, int b){
        bi = bi.valueOf(a);
        bi = bi.pow(b);
        char[] k = bi.toString().toCharArray(); 
        int sod = 0; 
        for(int i = k.length - 1; i >= 0; --i){
            sod += k[i] - '0';
        }
        return sod;
    }
}


