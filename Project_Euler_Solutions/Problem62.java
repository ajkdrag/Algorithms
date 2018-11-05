/*

  Problem at : https://projecteuler.net/problem=62

*/

import java.util.HashMap;

public class Problem62 {
    public static void main(String args[]) {
        System.out.println(Solution62.solve());
    }
}

class Solution62 {
    static HashMap<Long,Integer> perm_count;
    static int[] arr;
    static long solve(){
        perm_count = new HashMap<Long,Integer>();
        arr = new int[10032];
        long cube = 0L;
        long digit_c = 0L;
        for(long i = 345; i <= 10031; ++i){
            cube = i*i*i;
            digit_c = get_digitCount(cube);
            if(perm_count.containsKey(digit_c)){
                arr[perm_count.get(digit_c)]++;
            }
            else{
                perm_count.put(digit_c, (int)i);
                arr[(int)i]++;
            } 
        }
        
        for(long i = 345; i <= 10031; ++i){
            if(arr[(int)i] == 5){
                cube = i*i*i;
                return cube;
            }
        }
        return -1;
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


