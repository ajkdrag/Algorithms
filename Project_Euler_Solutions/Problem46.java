/*

  Problem at : https://projecteuler.net/problem=46

*/

import java.util.ArrayList;
public class Problem46 {
    public static void main(String args[]) {
        Solution46.solve(10000);
    }
}

class Solution46 {
    static int[] primes;
    static ArrayList<Integer> double_squares;
    
    static void solve(int n){
        int limit = n;
        primes = new int[limit + 1];
        primes[1] = 1;
        double_squares = new ArrayList<Integer>();
        double_squares.add(2);
        int i,j;
        
        for(i = 2; i <= limit; ++i){
            j = i*i;
            double_squares.add(j<<1);

            if(primes[i] == 0)
                for(; j <= limit; j+=i) primes[j] = 1;
                
            else if((i&1) == 1 && !satisfies(i)){
                System.out.println(i);
                break;
            }
        }
    }
    
    static boolean satisfies(int n){
        for(int s : double_squares){
            if(s >= n) break;
            int t = n - s;
            if(primes[t] == 0) {
                return true;
            }
        }
        return false;
    }
}


