/*
  Problem at : https://icpcarchive.ecs.baylor.edu/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1723
*/

import java.util.*;
import java.io.*;

public class Main{
    public static void main(String args[])  throws IOException{
        Scan scan = new Scan();
		Print print = new Print();
		while(true){
		    long x = scan.scanLong();
		    long a = scan.scanLong();
		    long n = scan.scanLong();
		    long c = scan.scanLong();
		    if(x + a + n + c == 0)
		        break;
		    print.println(solve(x, a, n, c));
		}
		print.close();
    }
    
    static long solve(long x, long a, long n, long c){
        a %= c;
        long pow = 1;
        long firstTerm = (x - 1)%c;
        if( a != 1){
            pow = modExp(a, n, c);
            firstTerm = (pow*(x - 1))%c;
        }
        
        if(n > 1){
            long secondTerm = (n - 1)%c;
            if(a != 1){
                secondTerm = (pow - a + c)%c;
                secondTerm = (secondTerm * modInv(a - 1, c))%c;
            }
            firstTerm = (firstTerm - secondTerm + c)%c;
        }
        return firstTerm ;
    }
    
    static long[] gcd(long p, long q) {
      if (q == 0)
         return new long[] { p, 1, 0 };
      long[] vals = gcd(q, p % q);
      long d = vals[0];
      long a = vals[2];
      long b = vals[1] - (p / q) * vals[2];
      return new long[] { d, a, b };
   }

   static long modInv(long k, long n) {
      long[] vals = gcd(k, n);
      long d = vals[0];
      long a = vals[1];
      long b = vals[2];
      if (d > 1) { System.out.println("Inverse does not exist."); return 0; }
      if (a > 0) return a;
      return n + a;
   }
    
    static long modExp(long base, long exp, long mod){
        if(exp == 0)
            return 1;
        long res = 1;
        while(exp > 0){
            if((exp &1) == 1)
                res = (res * base)%mod;
            base = (base * base)%mod;
            exp >>= 1;
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



