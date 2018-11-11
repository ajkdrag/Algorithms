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
        Binary Masks for 5-digit numbers : 
        a)  10001 = 17
        b)  01001 = 9
        c)  00101 = 5
        d)  00011 = 3
        
        Binary Masks for 6-digit numbers :
        a) 110001 = 49
        b) 101001 = 41
        c) 100101 = 37
        d) 100011 = 35
        e) 010011 = 19
        f) 001011 = 11
        g) 000111 = 7
        h) 011001 = 25
        i) 001101 = 13
        j) 010101 = 21
     */
     
    static int[] pent_mask = {17,9,5,3};
    static int[] hex_mask = {49,41,37,35,19,11,7,25,13,21};
    static int result = Integer.MAX_VALUE;
    
    static int solve(){
        for(int i = 11; i <= 999; i+=2){
            if(i%3 == 0 || i%5 == 0) continue;
            if(i >= 100) search(hex_mask, 6, i);
            else {
                search(pent_mask,5, i);
                search(hex_mask, 6, i);
            }
        }
        return result;
    }
    
    static void search(int[] maskArr, int mask_size, int num){
        int mask = 0;
        int res = 0;
        for(int i = maskArr.length - 1; i >= 0; --i){
            mask = maskArr[i];
            res = generate(mask, num, mask_size, 1);
            for(int k = 0; k <= 222; k += 111){
                if(k == 0 && (mask&(1<<(mask_size - 1))) == 0) continue;
                int number = res + generate(mask,k,mask_size,0);
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
            int number = res + generate(mask,num,mask_size, mask_check);
            if(isPrime(number)) {
                ++counter;
            }
        }
        return counter;
    }
    
    static int generate(int mask, int num, int mask_size, int mask_check){
        int p = 1;
        int res = 0;
        for(int j = mask_size - 1; j >= 0; --j){
            if((mask&1) == mask_check){
                int r = num%10;
                res += (r*p);
                num /= 10;
            }
            p *= 10;
            mask >>= 1;
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
}


