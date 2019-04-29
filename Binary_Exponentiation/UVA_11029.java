/*
  Problem at : https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1970
  The problem asks for the first 3 digits and the last 3 digits of n^k , where n and k are large. Clearly for the last 3 digits, we
  can use binary exponentiation and mod with 1000, since modding with 1000 will yield the last 3 digits. For the first 3 digits, it's a
  bit tricky. We need to use the help of logarithms :
    Suppose n = 456, k = 2 , n^k = 207936 ; say, y = n^k
    ==> log10(y) = k*log10(n)
    The R.H.S can be computed without overflow (if 'k' and 'n' aren't too big, problem states that k < 10000001, n fits in 32-bit int)
    ==> log10(y) = 2*log10(456)
    ==> log10(y) = 5.31792969
    ==> y = 10^(5.31....)
    ==> y = (10^(0.31...)) * (10^5)
    ==> y = 2.07... * (10^5)
    At this stage we can keep multiplying the 2.07... with 10, while it's less that 100
    ==> 2.07... becomes 20.7... becomes 207... and now we don't multiply with 10 anymore.
    Thus finally we have the leading 3 digits. Remember we only need to extract 0.31... from 5.317... (i.e the fractional part)
    and then raise 10 to that power.
    P.S : This approach works fairly well till 10^10 , beyond which rounding errors start to get significant.
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
		int t = scan.scanInt();
		while(t-- > 0){
		    int n = scan.scanInt();
		    int k = scan.scanInt();
		    print.println(String.format("%d...%03d",leading(n, k),trailing(n, k)));
		}
        print.close();
    }
    
    static int leading(int n, int k){
        double temp = k*Math.log10(n);
        double frac = temp - (int) temp;
        frac = Math.pow(10, frac);
        while(frac < 100)
            frac *= 10;
        return (int)frac;
    }
    
    static int trailing(int n, int k){
        if(k == 0)
            return 1;
        n %= 1000;
        if(k == 1)
            return n;
        int res = 1;
        while(k > 0){
            if((k&1) == 1)
                res = (res*n)%1000;
            n = (n*n)%1000;
            k >>= 1;
        }
        return res;
    }
}

// fast IO
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
        int integer=0;
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
                integer*=10;
                integer+=n-'0';
                n=scan();
            }
            else throw new InputMismatchException();
        }
        return neg*integer;
    }
    private boolean isWhiteSpace(int n)
    {
        if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
        return true;
        return false;
    }
    public boolean hasNext() throws IOException{
        if(index>=total)
        {
            index=0;
            total=in.read(buf);
            if(total<=0)
            return false;
        }
        return true;
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


