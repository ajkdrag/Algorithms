/*
  Problem at : https://www.spoj.com/problems/VOCV/
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scan  sc = new Scan();
        int t = sc.scanInt();
        while(t-- > 0){
            int n = sc.scanInt();
            Sol sol = new Sol(n);
            while(n-- > 1){
                sol.ae(sc.scanInt(), sc.scanInt());
            }
            sol.solve();
        }
    }
}

class Sol {
    static final int MOD = 10007;
    int n;
    List<Integer>[] al;
    int[][] dp, dp2;
    
    Sol(int n){
        this.n = n;
        al = new ArrayList[n+1];
        dp = new int[n+1][2];
        dp2 = new int[n+1][2];
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
        dp2[st][0] = 1;
        dp2[st][1] = 1;
        for(int nbr : al[st]){
            if(nbr == par)
                continue;
            dfs(nbr, st);
            dp[st][0] += dp[nbr][1];
            dp2[st][0] = (dp2[st][0] * dp2[nbr][1])%MOD;
            if(dp[nbr][1] < dp[nbr][0]){
                dp[st][1] += dp[nbr][1];
                dp2[st][1] = (dp2[st][1] * dp2[nbr][1])%MOD;
                continue;
            }
            else if(dp[nbr][1] > dp[nbr][0]){
                dp[st][1] += dp[nbr][0];
                dp2[st][1] = (dp2[st][1] * dp2[nbr][0])%MOD;
                continue;
            }
            else {
                dp[st][1] += dp[nbr][0];
                dp2[st][1] = (dp2[st][1] * (dp2[nbr][0] + dp2[nbr][1]))%MOD;
            }
        }
    }
    
    void solve(){
        dfs(1, 0);
        if(dp[1][0] < dp[1][1]){
            System.out.println(dp[1][0] + " " + dp2[1][0]);    
        }
        else if(dp[1][0] > dp[1][1]){
            System.out.println(dp[1][1] + " " + dp2[1][1]);
        }
        else{
            dp2[1][0] = (dp2[1][0]+dp2[1][1])%MOD;
            System.out.println(dp[1][0] + " " + dp2[1][0]);
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

