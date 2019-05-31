/*
  Problem at : https://www.codechef.com/problems/COPR16G
*/

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String args[]) throws IOException{
        Scan scan = new Scan();
		Print print = new Print();
		long t = scan.scanLong();
		while(t-- > 0){
		    long a = scan.scanLong();
		    long b = scan.scanLong();
		    long d = gcd(a, b);
        // application of chicken mcnugget problem/frobenius problem
		    if(d != 1)
		        print.println(-1);
		    else 
		        print.println((a-1)*(b-1));
		}
        print.close();
    }
    
    // the EA Algorithm
    static long gcd(long a, long b){
        if(b == 0)
            return a;
        return gcd(b, a%b);
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


