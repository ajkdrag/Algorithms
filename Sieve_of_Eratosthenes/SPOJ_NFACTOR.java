/*
  Problem at : https://www.spoj.com/problems/NFACTOR
*/

import java.io.*;
import java.util.InputMismatchException;

public class Main {
    public static void main(String args[]) throws IOException{
        Solution sol = new Solution();
        sol.fill();
        Scan sc = new Scan();
        Print pt = new Print();
        int t = sc.scanInt();
        while(t-- > 0){
            pt.println(sol.solve(sc.scanInt(), sc.scanInt(), sc.scanInt()));
        }
        pt.close();
    }
}

class Solution {
    int[][] sieve;
    int limit = 1000001;
    void fill(){
        sieve = new int[10][1000001];
        for(int i = 2; i < limit; ++i){
            if(sieve[0][i] == 0){
                sieve[0][i] = 1;
                for(int j = i<<1; j < limit; j+=i){
                    sieve[0][j] += 1;
                }
            }
            
            int a = sieve[0][i];
            for(int k = 0; k < 10; ++k){
                sieve[k][i] = sieve[k][i-1] + (a == (k+1) ? 1 : 0);
            }
        }
        
    }
    
    int solve(int a, int b, int n){
        if(n == 0){
            if(a <= 1)
                return 1;
            return 0;
        }
        return sieve[n-1][b] - sieve[n-1][a-1];
    }
}

// fast I/O
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


