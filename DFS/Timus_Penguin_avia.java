/*
  Problem at : http://acm.timus.ru/problem.aspx?space=1&num=1709
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Scan sc = new Scan();
        Print pt = new Print();
        int N = sc.scanInt();
        int D = sc.scanInt();
        int A = sc.scanInt();
        Solution sol = new Solution(N, A, D);
        for(int i = 1; i <= N; ++i){
            for(int j = 1; j <= N; ++j){
                int val = sc.scanBit();
                if(j < i && val == 1){
                    sol.solve(i, j);
                }
            }
        }
        int f = -1;
        for(int i = 1; i <= N; ++i){
            if(sol.set[i] < 0){
                if(f == -1){
                    f = i;
                    continue;
                }
                sol.mt[f][i] = 'a' - '0';
                sol.mt[i][f] = 'a' - '0';
            }
        }
        
        pt.print(sol.res + (sol.N - 1)*A);
        pt.println();
        for(int i = 1; i <= N; ++i){
            for(int j = 1; j <= N; ++j){
                pt.print((char)(sol.mt[i][j] + '0'));
            }
            pt.println();
        }
        pt.close();
    }
}

class Solution {
    int[] set;
    int[][] mt;
    int N;
    int A, D;
    long res = 0;
    
    Solution(int N, int a, int d){
        this.N = N;
        this.A = a;
        this.D = d;
        mt = new int[N+1][N+1];
        set = new int[N+1];
        for(int i = 1; i <= N; ++i)
            set[i] = -1;
    }
    
    int find(int n){
        int val = set[n];
        if(val < 0)
            return n;
        return set[n] = find(val);
    }
    
    boolean union(int a, int b){
        int par_a = find(a);
        int par_b = find(b);
        if(par_a == par_b)
            return false;
        if(par_a < par_b){
            int t = par_a;
            par_a = par_b;
            par_b = t;
        }
        set[par_a] += set[par_b];
        set[par_b] = par_a;
        
        --N;
        return true;
    }

    void solve(int a, int b){
        if(!union(a, b)){
            res += D;
            mt[a][b] = 'd' - '0';
            mt[b][a] = 'd' - '0';
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
    
    public int scanBit() throws IOException {
        int n=scan();
        while(isWhiteSpace(n))    //Removing starting whitespaces
        n=scan();
        return n-'0';
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
    public void println()throws IOException
    {
        bw.append("\n");
    }
    public void close()throws IOException
    {
        bw.close();
    }
}


