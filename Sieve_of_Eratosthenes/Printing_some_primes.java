/*
  Problem at : https://www.spoj.com/problems/PRIMES2/
*/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Solution sol = new Solution();
        sol.fillSieve();
    }
}

class Solution {
    int limit = 1000000000;
    int S = 31622;
    ArrayList<Integer> primes;
    int [] sieve_;
    static Print pt;
    
    void fillSieve() throws IOException{
        pt = new Print();
        primes = new ArrayList<Integer>();
        int cnt = 3401;
        int ct = 3500;
        pt.println(2);
        pt.println(3581);
        pt.println(7927);
        pt.println(12569);
        pt.println(17393);
        pt.println(22343);
        pt.println(27457);
        sieve_ = new int[1 + (S>>1)];
        for(long i = 3; i <= S; i+=2){
            if(sieve_[(int)i>>1] == 0){
                primes.add((int)i);
                for(long j = i*i; j <= S; j+=(i<<1))
                    sieve_[(int)j>>1] = 1;
            }
        }
        int[] sieve = new int[S];
        for (int k = 1; k * S <= limit; k+=2) {
            sieve = new int[S];
            int start = k * S + 1;
            for (int p : primes) {
                if(p*p > (start + (S - 1)<<1))
                    break;
                int r = start/p;
                int j = p*(1 + r);
                if(r*p == start)
                    j = 0;
                else {
                    if((j&1)==0){
                        j += p;
                    }
                    j = (j - start)>>1;
                }
                
                for (; j < S; j += p)
                    sieve[j] = 1;
            }
            if (k == 0){
                sieve[0] = 1;
                sieve[1] = 1;
            }
            for (int i = 0; i < S && start + (i<<1) <= limit; i++) {
                if (sieve[i] == 0 ){
                    if(cnt == ct){
                        pt.println((start + (i<<1)));
                        ct += 500;
                    }
                    ++cnt;
                }
            }
        }
        pt.close();
    }
    
}

class Print
{
    private final BufferedWriter bw;
    public Print()
    {
        this.bw=new BufferedWriter(new OutputStreamWriter(System.out));
    }
    public void print(Object object)throws IOException
    {
        bw.append(""+object);
    }
    public void println(Object object)throws IOException
    {
        print(object);
        bw.append("\n");
    }
    public void close()throws IOException
    {
        bw.close();
    }
}


