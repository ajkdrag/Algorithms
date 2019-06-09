/*
  Problem at : http://codeforces.com/contest/776/problem/B
*/

import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Solution sol = new Solution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sol.fillSieve(Integer.parseInt(br.readLine()));
    }
}

class Solution {
    int[] sieve;
    int[] res;
    
    void fillSieve(int n){
        sieve = new int[(n + 2)>>1];
        res = new int[(n + 2)>>1];
        sieve[0] = 1;
        for(int i = 3; i*i < n + 2; i+=2){
            if(sieve[i>>1] == 0){
                res[i>>1] = 1;
                for(int j = i*i; j < n + 2; j += (i<<1)){
                    sieve[j>>1] = 1;
                    res[j>>1] = 2;
                }
            }
        }
        if(n <= 2)
            System.out.println(1);
        else
            System.out.println(2);
        System.out.print(1 + " ");
        for(int i = 3; i < n + 2; ++i){
            if((i&1)==0){
                System.out.println(2 + " ");
                continue;
            }
            if(res[i>>1] == 0)
                res[i>>1] = 1;
            System.out.print(res[i>>1] + " ");
        }
    }
}


