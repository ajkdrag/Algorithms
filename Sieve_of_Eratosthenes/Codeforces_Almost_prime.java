/*
  Problem at : http://codeforces.com/contest/26/problem/A
*/

import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Solution sol = new Solution();
        sol.fillSieve();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sol.solve(Integer.parseInt(br.readLine()));
    }
}

class Solution {
    int[] sieve;
    int[] nums;
    int[] cum;
    int limit = 3001;
    
    void fillSieve(){
        sieve = new int[limit];
        nums = new int[limit];
        cum = new int[limit];
        sieve[0] = 1;
        sieve[1] = 1;
        
        for(int i = 2; i < limit>>1; i++){
            if(sieve[i] == 0){
                for(int j = i<<1; j < limit; j += i){
                    int val = sieve[j];
                    if(sieve[val] == 0){
                        int num = nums[j];
                        while((num%i) == 0){
                            num/=i;
                        }
                        if(num == 1)
                            cum[j] = 1;
                        sieve[j] = val*i;
                        nums[j] = num;
                    }
                    else {
                        if(val == 0){
                            sieve[j] = i;
                            int num = j;
                            while((num%i) == 0){
                                num/=i;
                            }
                            sieve[j] = i;
                            nums[j] = num;
                        }
                        else {
                            sieve[j] = 1;
                        }
                    }
                }
            }
        }
        
        
        for(int i = 1; i < limit; ++i){
            cum[i] += cum[i-1];
        }
    }
    
    void solve(int n){
        System.out.println(cum[n]);
    }
}


