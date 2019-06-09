/*
  Problem at : https://www.spoj.com/problems/PTRI2/
*/

import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Solution sol = new Solution();
        sol.fillSieve();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            sol.solve(Integer.parseInt(br.readLine()));
        }
    }
}

class Solution {
    int[] sieve;
    int[] rows;
    int[] cols;
    int limit = 100000000;
    
    void fillSieve(){
        sieve = new int[limit>>1];
        rows = new int[limit>>1];
        cols = new int[limit>>1];
        sieve[0] = 1;
        int row = 2;
        int col = 1;
        for(long i = 3; i < limit; i+=2){
            if(sieve[(int)i>>1] == 0){
                rows[(int)i>>1] = row;
                cols[(int)i>>1] = col;
                if(col == row){
                    row += 1;
                    col = 0;
                }        
                ++col;
                for(long j = i*i; j < limit; j+= (i<<1)){
                    sieve[(int)j>>1] = 1;
                }
            }
        }
    }
    
    void solve(int n){
        if((n&1) == 0 || sieve[n>>1] == 1){
            System.out.println(-1);
            return;
        }
        int row = rows[(n-1)>>1];
        int col = cols[(n-1)>>1];
        System.out.println(row + " " + col);
    }
}


