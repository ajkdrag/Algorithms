/*
  Problem at : https://www.spoj.com/problems/NGIRL/
*/

import java.io.*;
import java.util.InputMismatchException;

public class Main {
    static Print op;
    public static void main(String args[]) throws IOException{
        Solution sol = new Solution();
        sol.fillSieve();
        Scan ip = new Scan();
        op = new Print();
        int t = (int)ip.scanLong();
        while(t-- > 0){
            sol.solve(ip.scanLong(), ip.scanLong());
        }
        op.close();
    }
}

class Solution {
    int limit_ = 100000;
    int[] cum;
    int[] sieve;
    
    
    void fillSieve() {
        sieve = new int[(limit_>>1)];
        cum = new int[(limit_>>1)];
        cum[0] = 1;
        sieve[0] = 1;
        for(long i = 3; i <= limit_; i+=2){
            if(sieve[(int)i>>1] == 0){
                for(long j = i*i; j <= limit_; j+=(i<<1))
                    sieve[(int)j>>1] = 1;
            }
            cum[(int)i>>1] = (1 ^ sieve[(int)i>>1]) + cum[((int)i>>1) - 1];
        }
    }
    
    void solve(long n, long k) throws IOException{
        int sq_n = (int)Math.sqrt(n);
        int sq_k = (int)Math.sqrt(k);
        int a = cum[(sq_n-1)>>1];
        int b = cum[(sq_k-1)>>1];
        if(k <= 2)
            b = 0;
        if(b > a)
            b = a;
        Main.op.println(a + " " + (a-b));
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
    public long scanLong()throws IOException
    {
        long big=0;
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


