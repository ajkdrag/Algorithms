/*
  Problem at : https://codeforces.com/problemset/problem/279/B
*/

import java.util.*;
import java.io.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        Scan sc = new Scan();
        int n = sc.scanInt();
        Sol sol = new Sol(n, sc.scanInt());
        for(int i = 0; i < n; ++i){
            sol.add(i, sc.scanInt());
        }
        sol.solve();
    }
}
 
class Sol {
    int[] arr;
    int n, limit;
    Sol(int n, int a){
        this.n = n;
        limit = a;
        arr = new int[n];
    }
    
    void add(int i, int val){
        arr[i] = val;
    }
    
    void solve(){
        int l = 0;
        int r = 0;
        int best = 0;
        int currSum = 0;
        while(r < n){
            if(l > r){
                r=l;
                currSum = 0;
                continue;
            }
            currSum +=  arr[r];
            if(currSum > limit){
                while(currSum > limit){
                    currSum -= (arr[l++]);  
                }
                ++r;
            }
            else {
                best = Math.max(best, r-l+1);
                ++r;
            }
        }
        System.out.println(best);
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

