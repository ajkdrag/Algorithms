/*
  Problem at : https://www.spoj.com/problems/PT07X/
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scan  sc = new Scan();
        int n = sc.scanInt();
        Sol sol = new Sol(n);
        while(n-- > 1){
            sol.ae(sc.scanInt(), sc.scanInt());
        }
        sol.solve();
    }
}

class Sol {
    int n;
    List<Integer>[] al;
    int[][] dp;
    
    Sol(int n){
        this.n = n;
        al = new ArrayList[n+1];
        dp = new int[n+1][2];
        for(int i = 1; i <= n; ++i){
            al[i] = new ArrayList<Integer>();
        }
    }
    void ae(int a, int b){
        al[a].add(b);
        al[b].add(a);
    }
    
    void dfs(int st, int par){
        dp[st][1] = 1;
        for(int nbr : al[st]){
            if(nbr == par)
                continue;
            dfs(nbr, st);
            dp[st][0] += dp[nbr][1];
            dp[st][1] += Math.min(dp[nbr][1], dp[nbr][0]);
        }
    }
    
    void solve(){
        dfs(1, 0);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
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

