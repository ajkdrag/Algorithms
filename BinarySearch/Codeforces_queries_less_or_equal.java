/*
  Problem at : https://codeforces.com/problemset/problem/600/B
*/

import java.util.*;
import java.io.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        Scan sc = new Scan();
        int m = sc.scanInt();
        int n = sc.scanInt();
        Sol sol = new Sol(m, n);
        for(int i = 0; i < m; ++i)
            sol.add1(i, sc.scanInt());
        for(int i = 0; i < n; ++i)
            sol.add2(i, sc.scanInt());
        sol.solve();
    }
}
 
class Sol {
    int[] orig2;
    Integer[] orig1, copy2;
    HashMap<Integer, Integer> vals;
    int m, n;
    
    Sol(int m, int n){
        this.m = m;
        this.n = n;
        copy2 = new Integer[n];
        orig1 = new Integer[m];
        orig2 = new int[n];
        vals = new HashMap<Integer, Integer>();
    }
    
    void add1(int i, int val){
        orig1[i] = val;
    }
    
    void add2(int i, int val){
        orig2[i] = val;
        copy2[i] = val;
    }
    
    void solve(){
        Arrays.sort(copy2);
        Arrays.sort(orig1);
        int start = 0;
        int end = m;
        for(int i : copy2){
            int curr = bSrch(start, end, i);
            vals.put(i, curr+1);
            start = curr+1;
        }
        for(int i : orig2)
            System.out.print(vals.get(i) + " ");
    }
    
    int bSrch(int st, int ed, int key){
       while(st < ed){
           int mid = (st+ed)/2;
           if(orig1[mid] > key)
                ed = mid;
            else
                st = mid + 1;
       } 
       return st-1;
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


