/*
  Problem at : https://codeforces.com/contest/813/submission/59338906
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main (String[] args) throws IOException {
        Scan reader = new Scan();
        int n = reader.scanInt();
        int B = reader.scanInt();
        Sol sol = new Sol(n, B);
        for(int i = 1; i < n; ++i){
            sol.ae(reader.scanInt(), reader.scanInt());
        }
        sol.getMaxD(1, 0);
        sol.run(1, 0, 0);
        // System.out.println(Arrays.toString(sol.BTime));
        System.out.println(sol.res * 2);
    }
}
 
class Sol {
    List<Integer>[] al;
    int[] ATime, BTime, maxD;
    int res = 0;
    int n,B;
    
    Sol (int n, int B){
        this.n = n;
        this.B = B;
        al = new ArrayList[n+1];
        ATime = new int[n+1];
        BTime = new int[n+1];
        maxD = new int[n+1];
        for(int i = 1; i <= n; ++i)
            al[i] = new ArrayList<Integer>();
    }
    
    int getMaxD(int st, int par){
        int max = 0;
        for(int nbr : al[st]){
            if(nbr == par) 
                continue;
            int temp = 1 + getMaxD(nbr, st);
            max = temp > max ? temp : max;
        }    
        return maxD[st] = max;
    }
    
    int run (int st, int par, int atime){
        ATime[st] = atime;
        BTime[st] = Integer.MAX_VALUE;
        if(st == B){
            BTime[st] = 0;
            res = Math.max(res, atime + maxD[st]);
            return 0;
        }
        int min = Integer.MAX_VALUE - 1;
        for(int nbr : al[st]){
            if(nbr == par)
                continue;
            int temp = run(nbr, st, atime + 1);
            min = temp < min ? temp : min;
        }
        if(min + 1 < atime){
            res = Math.max(res, atime + maxD[st]);
        }
        return BTime[st] = min + 1;
    }
    
    void ae(int a, int b){
        al[a].add(b);
        al[b].add(a);
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


