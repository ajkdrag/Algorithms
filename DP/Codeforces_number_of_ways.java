/*
  Problem at : https://codeforces.com/contest/466/problem/C
*/

import java.util.*;
import java.io.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        Scan sc = new Scan();
        int n = sc.scanInt();
        Sol sol = new Sol(n);
        for(int i = 1; i <= n; ++i)
            sol.add(i, sc.scanInt());
        sol.solve();
    }
}
 
class Sol {
    long[] arr, cnt;
    int n;
    long res;
    
    Sol(int n){
        this.n = n;
        arr = new long[n+1];
        cnt = new long[n+1];
    }
    
    void add(int i, int val){
        arr[i] = arr[i-1]+ val;
    }
    
    void solve() {
        long s = arr[n];
        if (s % 3 != 0)
            System.out.println(0);
        else {
            s /= 3;
            long ss = 0;
            for(int i = n-1; i > 0; --i)
            {
                if(arr[n]-arr[i] == s)
                    cnt[i] = 1;
                cnt[i] += (i < n ? cnt[i+1] : 0);
            }
            ss = 0;
            for(int i = 2; i < n; ++i)
                if (arr[i-1] == s)
                    res += cnt[i];
            System.out.println(res);
        }
    }
    
}
 
class Scan
{
    private byte[] buf=new byte[1024];
    private int index;
    private InputStream in;
    private int total;
    public Scan()
    {
        in=System.in;
    }
    public int scan()throws IOException
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
        while(isWhiteSpace(n))
        n=scan();
        int neg=1;
        if(n=='-')
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
    public double scanDouble()throws IOException
    {
        double doub=0;
        int n=scan();
        while(isWhiteSpace(n))
        n=scan();
        int neg=1;
        if(n=='-')
        {
            neg=-1;
            n=scan();
        }
        while(!isWhiteSpace(n)&&n!='.')
        {
            if(n>='0'&&n<='9')
            {
                doub*=10;
                doub+=n-'0';
                n=scan();
            }
            else throw new InputMismatchException();
        }
        if(n=='.')
        {
            n=scan();
            double temp=1;
            while(!isWhiteSpace(n))
            {
                if(n>='0'&&n<='9')
                {
                    temp/=10;
                    doub+=(n-'0')*temp;
                    n=scan();
                }
                else throw new InputMismatchException();
            }
        }
        return doub*neg;
    }
    public String scanString()throws IOException
    {
        StringBuilder sb=new StringBuilder();
        int n=scan();
        while(isWhiteSpace(n))
        n=scan();
        while(!isWhiteSpace(n))
        {
            sb.append((char)n);
            n=scan();
        }
        return sb.toString();
    }
    private boolean isWhiteSpace(int n)
    {
        if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
        return true;
        return false;
    }
}

