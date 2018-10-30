/*

  Problem at : https://projecteuler.net/problem=51

*/

public class Problem51 {
    public static void main(String args[]) {
        System.out.println(Solution51.solve());
    }
}

class Solution51 {
    /*
        BCD Masks for 5-digit numbers : 
        a)  10001 = 15*16^4 + 0 + 0 + 0 + 15*16^0 = 983055
        b)  01001 = 0 + 15*16^3 + 0 + 0 + 15*16^0 = 61455
        c)  00101 = 0 + 0 + 15*16^2 + 0 + 15*16^0 = 3855 
        d)  00011 = 0 + 0 + 0 + 15*16^1 + 15*16^0 = 255
        
        BCD Masks for 6-digit numbers :
        a) 110001 = 15*16^5 + 15*16^4 + 15*16^0 = 16711695
        b) 101001 = 16^5 + 16^3 + 16^0 = 15790095
        c) 100101 = 16^5 + 16^2 + 16^0 = 15732495
        d) 100011 = 16^5 + 16^1 + 16^0 = 15728895
        e) 010011 = 16^4 + 16^1 + 16^0 = 983295
        f) 001011 = 16^3 + 16^1 + 16^0 = 61695
        g) 000111 = 16^2 + 16^1 + 16^0 = 4095
        h) 011001 = 16^4 + 16^3 + 16^0 = 1044495
        i) 001101 = 16^3 + 16^2 + 16^0 = 65295
        j) 010101 = 16^4 + 16^2 + 16^0 = 986895
     
     */
    static int[] pent_mask = {983055,61455,3855,255};
    static int[] hex_mask = {16711695,15790095,15732495,15728895,983295,61695,4095,1044495,65295,986895};
    static int result = Integer.MAX_VALUE;
    
    static int solve(){
        for(int i = 11; i <= 999; i+=2){
            if(i%5 == 0 || i%3 == 0) continue;
            if(i < 100) search(pent_mask, 5, i);
            else search(hex_mask, 6, i);
        }
        return result;
    }
    
    static void search(int[] maskArr, int mask_size, int num){
        int mask = 0;
        int res = 0;
        for(int i = maskArr.length - 1; i >= 0; --i){
            mask = maskArr[i];
            res = generate(mask, num, mask_size, 15);
            for(int k = 0; k <= 222; k += 111){
                if(k == 0 && (mask&(15<<((mask_size - 1)<<2))) == 0) continue;
                int number = getBCD(res + generate(mask,k,mask_size,0),mask_size);
                if(number < result && isPrime(number)){
                    if(prime_famSize(res,mask,k+111,mask_size,0) == 8) result = number;
                    break;
                }
            }
        }
    }
    
    static int prime_famSize(int res, int mask, int num, int mask_size, int mask_check){
        int counter = 1;
        for(; num <= 999; num += 111){
            int number = getBCD(res + generate(mask,num,mask_size, mask_check),mask_size);
            if(isPrime(number)) {
                ++counter;
            }
        }
        return counter;
    }
    
    static int generate(int mask, int num, int mask_size, int mask_check){
        int p = 0;
        int res = 0;
        for(int j = mask_size - 1; j >= 0; --j){
            if((mask&15) == mask_check){
                int r = num%10;
                res |= (r<<p);
                num /= 10;
            }
            p += 4;
            mask >>= 4;
        }
        return res;
    }
    
    static boolean isPrime(int n) {
        if(n < 2) return false;
        if(n == 2 || n == 3) return true;
        if((n&1)==0 || n%3 == 0) return false;
        for(int i = 6; i*i <= n; i += 6) {
            if(n%(i-1) == 0 || n%(i+1) == 0) return false;
        }
        return true;
    }
    
    static int getBCD(int n, int size){
        int num = 0;
        int p = 1;
        int res = 0;
        while(n > 0){
            res += (n&15)*p;
            n>>=4;
            p*=10;
        }
        return res;
    }
}


