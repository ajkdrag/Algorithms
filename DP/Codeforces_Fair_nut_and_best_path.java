/*
  Problem at : https://codeforces.com/problemset/problem/1083/A
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        Scan sc = new Scan();
        int n = sc.scanInt();
        Sol sol = new Sol(n);
        for(int i = 1; i <= n; ++i){
            sol.w[i] = sc.scanInt();
        }
        for(int i = 1; i < n; ++i){
            sol.ae(sc.scanInt(), sc.scanInt(), sc.scanInt());
        }
        sol.dfs(1, 0);
        System.out.println(sol.res);
    }
}
 
class Sol {
    int n;
    long res;
    int[] w;
    long[] dp;
    ArrayList<Node>[] al;
    
    Sol(int n){
        this.n = n;
        w = new int[n+1];
        dp = new long[n+1];
        al = new ArrayList[n+1];
        for(int i = 1; i <= n; ++i)
            al[i] = new ArrayList<Node>();
    }
    
    void dfs(int st, int par){
        long max1 = 0;
        long max2 = 0;
        long curr = 0;
        for(Node nbr : al[st]){
            if(nbr.val == par)
                continue;
            dfs(nbr.val, st);
            curr =  dp[nbr.val] + w[nbr.val] + nbr.wt;
            if(curr > max1){
                max2 = max1;
                max1 = curr;
            }
            else if(curr > max2){
                max2 = curr;
            }
            dp[st] = curr > dp[st] ? curr : dp[st];
        }
        curr = max1 + max2 + w[st];
        res = curr > res ? curr : res;
    }
    
    void ae(int a, int b, int wt){
        al[a].add(new Node(b, -wt));
        al[b].add(new Node(a, -wt));
    }
}
 
class Node {
    int val;
    int wt;
    Node(int val, int wt){
        this.val = val;
        this.wt = wt;
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

