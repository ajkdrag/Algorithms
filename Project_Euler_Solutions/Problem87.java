/*
  Problem at : https://projecteuler.net/problem=87
*/

import java.util.ArrayList;
import java.util.HashSet;
public class Problem100 {
    public static void main(String args[]) {
        int lim = 7071;
        int[] nums = new int[lim];
        ArrayList<Integer> primes = new ArrayList<Integer>();
        HashSet<Integer> set = new HashSet<>();
        primes.add(2);
        for(int i = 3; i < lim; i += 2){
            if(nums[i] == 0){
                primes.add(i);
                for(int j = i*i; j < lim; j += (i << 1)){
                    nums[j] = 1;
                }
            }
        }
        int n = primes.size();
        int count = 0;
        for(int i = 0; i < n; ++i){
            int p1 = primes.get(i);
            for(int j = 0; j < n; ++j){
                int p2 = primes.get(j);
                if(p2 > 368)
                    break;
                int target = 50000000 - (p1*p1 + p2*p2*p2);
                if(target < 16)
                    break;
                for(int k = 0; k < n; ++k){
                    int p3 = primes.get(k);
                    int sum = target - (p3*p3*p3*p3);
                    if(sum < 0)
                        break;
                    if(!set.contains(sum))
                        ++count;
                    set.add(sum);
                }
            }
        }
        System.out.println(count);
    }
}


