/*
  Problem at : https://projecteuler.net/problem=97
*/

public class Problem97 {
    public static void main(String args[]) {
        long m = 10000000000L;
        int exp = 7830457;
        int temp = (exp - 9)%1562500;
        long res = mod_product(mod_exp(temp + 9, m) , 28433, m);
        System.out.println(res + 1);
    }
    
    static long mod_exp(long exp, long mod){
        long curr = 2;
        long res = 1;
        while(exp > 0){
            if((exp & 1) == 1)
                res = mod_product(res, curr, mod);
            curr = mod_product(curr, curr, mod);
            exp >>= 1;
        }
        return res;
    }
    
    static long mod_product(long a, long b, long mod){
        long res = 0; 
        while (b > 0){
            if ((b & 1) == 1) 
                res = (res + a) % mod; 
            a = (a << 1) % mod; 
            b >>= 1; 
        } 
        return res; 
    }
}


