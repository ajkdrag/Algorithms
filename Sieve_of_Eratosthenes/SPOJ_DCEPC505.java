/*
  Problem at : http://www.spoj.com/problems/DCEPC505
*/

import java.io.*;
import java.util.InputMismatchException;

public class MyClass {
    public static void main(String args[]) throws IOException{
        Solution sol = new Solution();
        sol.fillSieve();
        Scan sc = new Scan();
        Print pt = new Print();
        int t = sc.scanInt();
        while(t-- > 0){
            pt.println(sol.cum[sc.scanInt()]);
        }
        pt.close();
    }
}

class Solution {
    int[] sieve;
    int[] cum;
    int limit = 11000005 // MAX*loglog(MAX) , where MAX = 2000000;
    
    void fillSieve(){
        sieve = new int[limit];
        cum = new int[limit];
        sieve[0] = 1;
        sieve[1] = 1;
        
        int ct = 1;
        for(int i = 2; i < limit; i++){
            if(sieve[i] == 0){
                for(int j = i<<1; j < limit; j += i){
                    int a = j/i;
                    sieve[j] = 1;
                    if(a < i && sieve[a] == 0){
                        cum[j] = 1;
                    }
                }
            }
            if(cum[i] == 1){
                cum[ct++] = i;
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


