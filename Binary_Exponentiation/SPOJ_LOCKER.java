/*
  Problem at : https://www.spoj.com/problems/LOCKER/
*/

import java.io.*;
import java.util.InputMismatchException;
import java.util.HashMap;

public class Main {
    static final long MOD = 1000000007;
    public static void main(String args[]) throws IOException{
        Scan scan = new Scan();
		Print print = new Print();
		long t = scan.scanLong();
		while(t-- > 0){
		    long n = scan.scanLong();
		    print.println(solve(n));
		}
		print.close();
    }
    
    static long solve(long n){
        if(n < 5)
            return n;
        long exp = (n/3);
        long rem = n%3;
        if(rem == 1)
            return (modExp(3, exp - 1)*4)%MOD;
        if(rem == 2)
            return (modExp(3, exp)*(rem))%MOD;
        return modExp(3, exp)%MOD;
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


