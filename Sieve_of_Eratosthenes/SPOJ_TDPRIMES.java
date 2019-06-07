/*
  Problem at : https://www.spoj.com/ranks/TDPRIMES/start=2060
*/

public class Main {
    public static void main(String args[]) {
        Solution sol = new Solution();
        sol.solve();
    }
}

class Solution {
    int[] sieve;
    int limit = 100000000;
    void solve(){
        sieve = new int[limit>>1];
        sieve[0] = 1;
        int cnt = 101;
        int curr = 1;
        int val = -1;
        System.out.println(2);
        for(long i = 3; i < limit; i+=2){
            if(sieve[((int)i)>>1] == 0){
                ++curr;
                if(curr == cnt){
                    System.out.println(i%100);
                    cnt += 100;
                }
                for(long j = i*i; j < limit; j += (i<<1)){
                    sieve[((int)j)>>1] = 1;
                }
            }
        }
    }
}


