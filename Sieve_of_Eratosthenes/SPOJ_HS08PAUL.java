/*
  Problem at : https://www.spoj.com/problems/HS08PAUL/
*/

// accepted solution
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException{
        Solution sol = new Solution();
        sol.precomp();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            System.out.println(sol.solve(Integer.parseInt(br.readLine())));
        }
    }
}

class Solution {
    int[] sieve;
    int[] cum;
    int limit = 10000000;
    
    int solve(int n){
        if(n < 3)
            return n - 1;
        return cum[(n-1)>>1];
    }
    
    void fillSieve(){
        sieve = new int[limit >> 1];
        for(int i = 3; i*i < limit; i+=2){
            if(sieve[i>>1] == 0){
                for(int j = i*i; j < limit; j += (i<<1))
                    sieve[j>>1] = 1;
            }
        }
    }
    
    void precomp(){
        fillSieve();
        int val = -1;
        cum = new int[limit >> 1];
        cum[0] = 1;
        for(int x = 1; x < 3163; ++x){
            for(int y = 1; y < 57; ++y){
                val = x*x + y*y*y*y;
                if(val < limit && ((val&1) == 1) && (sieve[val>>1] == 0)){
                    cum[val>>1] = 1;
                }
            }
        }
                
        for(int i = 1; i < limit>>1; ++i)
            cum[i] += cum[i-1];
    }
}

// unaccepted yet noteworthy solution
import java.util.HashSet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException{
        Solution sol = new Solution();
        sol.precomp();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0){
            System.out.println(sol.solve(Integer.parseInt(br.readLine())));
        }
    }
}

class Solution {
    HashSet<Integer> sq;
    int[] sieve;
    int[] sq_sq;
    int[] cum;
    int limit = 10000000;
    int limit_sq = 3163;
    int limit_sq_sq = 57;
    
    int solve(int n){
        if(n < 3)
            return n - 1;
        return cum[(n-1)>>1];
    }
    
    void precomp(){
        sieve = new int[limit>>1];
        cum = new int[limit>>1];
        sq_sq = new int[limit_sq_sq];
        sq = new HashSet<Integer>();
        sieve[0] = 1;
        sq_sq[1] = 1;
        sq.add(1);
        int val = -1;
        int sq_cnt = 2;
        int sq_sq_cnt = 2;
        int res = 1;
        int temp = -1;
        for(long i = 3; i < limit; i+=2){
            if(sq_cnt < limit_sq){
                temp = sq_cnt * sq_cnt;
                sq.add(temp);
                ++sq_cnt;
            }
            if(sq_sq_cnt < limit_sq_sq){
                sq_sq[sq_sq_cnt] = temp*temp;
                ++sq_sq_cnt;
            }
            if(sieve[((int)i)>>1] == 0){
                for(int k = 1; k < limit_sq_sq && (val = sq_sq[k]) < i; ++k){
                    temp = (int)(i - val)%10;
                    if(temp == 2 || temp == 3 || temp == 7 || temp == 8)
                        continue;
                    if(sq.contains((int)i - val)){
                        ++res;
                        break;
                    }
                }
                
                for(long j = i*i; j < limit; j += (i<<1)){
                    sieve[((int)j)>>1] = 1;
                }
            }
            cum[((int)i)>>1] = res;
        }
    }
}


