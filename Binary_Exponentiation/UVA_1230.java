/*
  Problem at : https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=3671
  Basically, the problem simply asks for a fast exponentitation code. It can be solved by using binary-exponentiation, which takes
  log(n) multiplications since the exponent 'y' can be represented using log(y) bits and roughly that many multiplications are needed 
  instead of the usual O(n) multiplications needed in brute force (add to it issues regarding integer overflows etc).
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
    public static void main(String[] args) throws IOException{
        Scan scan = new Scan();
		Print print = new Print();
		int t = scan.scanInt();
		while(t-- > 0){
		    int x = scan.scanInt();
		    int y = scan.scanInt();
		    int m = scan.scanInt();
		    print.println(modExp(x, y, m));
		}
        print.close();
    }
    
    // non-recursive binary exponentiation function (we can use recursive approach as well)
    static int modExp(int x, int y, int m){
        x %= m;
        if(y == 0)
            return x;
        int res = 1;
        while(y > 0){
            if((y&1) == 1)
                res = (res*x)%m;
            x = (x*x)%m;
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
        if(total<0)
        throw new InputMismatchException();
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


