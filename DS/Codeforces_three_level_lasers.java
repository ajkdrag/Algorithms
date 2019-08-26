/*
  Problem at : https://codeforces.com/problemset/problem/924/B
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        Scan sc = new Scan();
        int n = sc.scanInt();
        int u = sc.scanInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; ++i)
            arr[i] = sc.scanInt();
        Sol sol = new Sol(n,  u);
        System.out.println(sol.solve(arr));
    }
}
 
class Sol {
    double res = -1;
    int n;
    int u;
    Sol(int n , int u){
        this.n = n;
        this.u = u;
    }
    double solve(int[] arr){
        if(n < 3)
            return res;
        int i = n - 1;
        int j = n - 3;
        while(i >= 0 && j >= 0){
            int valA = arr[i];
            int valB = arr[j];
            int diff = valA - valB;
            if(diff >= 0 && diff <= u){
                // compute
                double temp = diff;
                if(i - j >= 2){
                    temp = (valA - (arr[j+1]))/(double)diff;
                    res = Math.max(res, temp);
                }
                --j;
                continue;
            }
            else
                --i;
        }
        return res;
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
  
  
