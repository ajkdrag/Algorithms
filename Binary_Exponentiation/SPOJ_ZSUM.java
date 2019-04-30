/*
  Problem at : https://www.spoj.com/problems/ZSUM/
  Simplify the expression by getting rid of the cancellable terms. Remaining terms can be solved using binary exponentiation.
*/

import java.io.*;
import java.util.InputMismatchException;
import java.util.HashMap;

public class Main {
    static final long MOD = 10000007;
    public static void main(String args[]) throws IOException{
        Scan scan = new Scan();
		Print print = new Print();
		while(true){
		    long n = scan.scanLong();
		    long k = scan.scanLong();
		    if(n == 0 && k == 0)
		        break;
		    print.println(solve(n, k));
		}
		print.close();
    }
    
    static long solve(long n, long k){
        // the expression simplifies to the one below (after getting rid of the cancellable terms)
        long res = modExp(n, n) + modExp(n, k) + ((modExp(n - 1, n - 1) + modExp(n - 1, k))<<1);
        return res%MOD;
    }
    
    static long modExp(long x, long y){
        if(y == 0)
            return 1;
        x %= MOD;
        if(y == 1)
            return x;
        long res = 1;
        while(y > 0){
            if((y&1) == 1)
                res = (res*x)%MOD;
            x = (x*x)%MOD;
            y >>= 1;
        }
        return res;
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


