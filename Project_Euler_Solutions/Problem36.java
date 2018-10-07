/*

  Problem at : https://projecteuler.net/problem=36

*/

public class Problem36 {
    public static void main(String args[]) {
        System.out.println(Solution36.solve(1000000));
    }
}

class Solution36 {
    static int solve(int limit){
        limit = (((limit + 1) >>1)<<1) - 1;
        int sum = 0;
        for(int i = limit; i >= 1; i-=2){
            if(checkPal_Decimal(i) && checkPal_Binary(i)) sum += i;
        }
        return sum;
    }
    
    static boolean checkPal_Binary(int n){
        int n_bits = 0;
        int cpy = n;
        while(cpy > 0){
            cpy>>=1;
            n_bits++;
        }
        
        int r = 1;
        int l = 1<<(n_bits-1);
        
        while(l > r){
            if(((l&n) > 0 && (r&n) == 0) || ((l&n) == 0 && (r&n) > 0)) return false;
            l>>=1;
            r<<=1;
        }
        return true;
    }
    
    static boolean checkPal_Decimal(int num){
        int reversedInteger = 0, remainder, originalInteger;
        originalInteger = num;

        while( num != 0 )
        {
            remainder = num % 10;
            reversedInteger = reversedInteger * 10 + remainder;
            num  /= 10;
        }

        if (originalInteger == reversedInteger)
            return true;
        else
            return false;
    }
}

class Solution36_Alternate {
    
}

