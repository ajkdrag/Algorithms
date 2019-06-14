/*
  Problem at : https://codeforces.com/problemset/problem/17/A
*/

import java.io.*;
import java.util.InputMismatchException;

public class Main {
    public static void main(String args[]) throws IOException{
        Solution sol = new Solution();
        sol.fillSieve();
        Scan sc = new Scan();
        System.out.println(sol.solve(sc.scanInt(), sc.scanInt()));
    }
}

class Solution {
    int limit = 1000;
    int[] sieve;
    
    void fillSieve(){
        sieve = new int[limit>>1];
        int prev = 2;
        for(int i = 3; i <= limit; i+=2){
            int mark = sieve[i>>1];
            if(mark < 1){
                int toMark = (i + prev + 1)>>1;
                if(i != 3 && toMark < limit>>1 && sieve[toMark]==0)
                    sieve[toMark] = -1;
                for(int j = i*i; j <= limit; j += (i<<1)){
                    sieve[j>>1] = 1;
                }
                prev = i;
            }
            
            int val = sieve[(i>>1) - 1];
            if(mark == -1){
                sieve[i>>1] = val + 1;
            }
            else
                sieve[i>>1] = val;
        }
    }
    
    String solve(int n, int k){
        if(sieve[(n-1)>>1] >= k)
            return "YES";
        return "NO";
    }
}

class Scan
{
    private byte[] buf=new byte[1024];    //Buffer of Bytes
    private int index;
    private InputStream in;
    private int total;
    public Scan()
    {
        in=System.in;
    }
    public int scan()throws IOException    //Scan method used to scan buf
    {
        if(index>=total)
        {
            index=0;
            total=in.read(buf);
            if(total<=0)
            return -1;
        }
        return buf[index++];
    }
    public int scanInt()throws IOException
    {
        int big=0;
        int n=scan();
        while(isWhiteSpace(n))    //Removing starting whitespaces
        n=scan();
        int neg=1;
        if(n=='-')                //If Negative Sign encounters
        {
            neg=-1;
            n=scan();
        }
        while(!isWhiteSpace(n))
        {
            if(n>='0'&&n<='9')
            {
                big*=10;
                big+=n-'0';
                n=scan();
            }
            else throw new InputMismatchException();
        }
        return neg*big;
    }
    private boolean isWhiteSpace(int n)
    {
        if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
        return true;
        return false;
    }
}


