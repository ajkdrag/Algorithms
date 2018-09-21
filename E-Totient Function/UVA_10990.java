/*
  Problem at : https://uva.onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1931
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
        SolutionSODF.fillPhiSieve(2000000);
        SolutionSODF.fillPreSums(2000000);
        Scan scan=new Scan();
		Print print=new Print();
		int t = scan.scanInt();
		while(t-- > 0){
		    int a = scan.scanInt();
		    int b = scan.scanInt();
		    print.println(SolutionSODF.solve(a,b));
		}
        print.close();
    }
}

class SolutionSODF {
    static int[] phiSieve;
    static int[] preSums;
    static int len;
    
    static int solve(int a, int b){
        // int res = 0;
        // for(int i = a; i <= b; ++i){
        //     res += getSteps(i);
        // }
        return preSums[b] - preSums[a - 1];
    }
    
    static void fillPreSums(int n){
        preSums = new int[n + 1];
        preSums[0] = 0;
        preSums[1] = 0;
        preSums[2] = 1;
        for(int i = 3; i <= n; ++i){
            preSums[i] += preSums[i-1] + getSteps(i);
        }
    }
    
    static int getSteps(int n){
        if(n == 2) return 1;
        if(n == 1) return 0;
        if((n&1) == 0){
            n >>= 1;
            if((n&1) == 1) return getSteps(n);
            return 1 + getSteps(n);
        }  
        return 1 + getSteps(getPhi(n));
    }
    
    static int getPhi(int n){
        if((n&1) == 1) return phiSieve[n>>1];
        int res = 1;
        while((n&1) == 0) {
            res = res << 1;
            n >>= 1;
        }
        
        res >>= 1;
        return res * phiSieve[n>>1];
    }
    
    static void fillPhiSieve(int limit){
        len = (limit + 1) >> 1;
        phiSieve = new int[len];
        
        phiSieve[0] = 1;
        phiSieve[1] = 2;
        
        for(int i = 2; i < len; ++i){
            phiSieve[i] = i<<1;
        }
        
        for(int i = 3; i <= limit>>1; i += 2){
            if(phiSieve[i>>1] == i - 1){
                for(int j = (i<<2) - i; j <= limit; j += i<<1){
                    int val = phiSieve[j>>1];
                    if(val == (j - 1)) {
                        phiSieve[j>>1] = j - j/i;
                    }
                    else phiSieve[j>>1] -= val/i;
                }
            }
        }
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
