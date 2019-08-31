/*
  Problem at : https://codeforces.com/contest/837/problem/C
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main (String[] args) throws IOException {
        Scan sc = new Scan();
        int n= sc.scanInt();
        int x = sc.scanInt();
        int y = sc.scanInt();
        Sol sol = new Sol(n, x, y);
        for(int i = 1; i <= n; ++i){
            sol.put(i, sc.scanInt(), sc.scanInt());
        }
        sol.solve();
    }
}
 
class Sol {
    int n,a,b;
    int[][] dp;
    int[] xs,ys,cnt;
    
    Sol(int n, int a, int b){
        if(a > b){
            int t = a;
            a =  b;
            b = t;
        }
        this.n = n;
        this.a = a;
        this.b = b;
        dp = new int[101][101];
        xs = new int[n+1];
        ys = new int[n+1];
        cnt = new int[n+1];
    }
    
    void put(int i, int x, int y){
        if(x > y){
            int t = x;
            x =  y;
            y = t;
        }
        if(dp[x][y] != 0){
            cnt[i] = 1 + cnt[dp[x][y]];
        }
        dp[x][y] = i;
        xs[i] = x;
        ys[i] = y;
    }
    
    void solve(){
        for(int i = 1; i <= a; ++i){
            for(int j = i; j <= b; ++j){
                if(dp[i][j] == 0){
                    if(xs[dp[i-1][j]]*ys[dp[i-1][j]] > xs[dp[i][j-1]]*ys[dp[i][j-1]]){
                        dp[i][j] = dp[i-1][j];
                    }
                    else
                        dp[i][j] = dp[i][j-1];
                }
            }
        }
        int max = 0;
        for(int i = 1; i <= n; ++i){
            int currX = xs[i];
            int currY = ys[i];
            int currA = currX*currY;
            if(currX <= a && currY <= b){
                int val1 = dp[a-currX][b];
                if(val1 == i)
                    val1 = 0;
                int val2 = dp[a][b-currY];
                if(b-currY < a){
                    val2 = dp[b-currY][a];
                }
                if(val2 == i)
                    val2 = 0;
                int currB = Math.max(xs[val1]*ys[val1], xs[val2]*ys[val2]);
                if(currB > 0){
                    max = Math.max(max, currA + currB);
                }
            }
            if(currX <= b && currY <= a){
                int val1 = dp[a-currY][b];
                if(val1 == i)
                    val1 = 0;
                int val2 = dp[a][b-currX];
                if(b-currX < a){
                    val2 = dp[b-currX][a];
                }
                if(val2 == i)
                    val2 = 0;
                int currB = Math.max(xs[val1]*ys[val1], xs[val2]*ys[val2]);
                if(currB > 0){
                    max = Math.max(max, currA + currB);
                }
            }
        }
        System.out.println(max);
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

