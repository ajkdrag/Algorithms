/*
  Problem at : http://codeforces.com/contest/723/problem/E
*/

import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException{
        Scan sc = new Scan();
        int t = sc.scanInt();
        while(t-- > 0){
            int n = sc.scanInt();
            int m = sc.scanInt();
            Sol sol = new Sol(n);
            while(m-- > 0){
                int a = sc.scanInt();
                int b = sc.scanInt();
                sol.ae(a, b);
            }
            sol.solve();
        }
    }
}

class Sol {
    int[][] al;
    int[] in, visited;
    int[] added;
    int n;
    Stack<Integer> st;
    int res;
    
    Sol (int n){
        this.n = n;
        al = new int[n+1][n+1];
        in = new int[n+1];
        added = new int[n+1];
        visited = new int[n+1];
        st = new Stack<Integer>();
        res = 0;
    }
    
    // add edge
    void ae(int a, int b){
        al[a][b] = 1;
        al[b][a] = 1;
        in[a]++;
        in[b]++;
    }
    
    // add temporary edges between vertices with odd degree (refer to editorial)
    void at(){
        int oe = 0;
        int prev = -1;
        for(int i = 1; i <= n; ++i){
            if((in[i]&1)== 1){
                ++oe;
                oe &= 1;
                if(oe==0){
                    al[prev][i] += 1;
                    al[i][prev] += 1;
                    int small = prev;
                    int big = i;
                    if(i < prev){
                        small = i;
                        big = prev;
                    }
                    added[small] = big;
                }
                else
                    prev = i;
            }
            else
                ++res;
        }
    }
    
    // euler tour
    void et(int sc) {
        visited[sc] = 1;
        for(int i = 1; i <= n; ++i){
            if(al[sc][i] > 0){
                al[sc][i] -= 1;
                al[i][sc] -= 1;
                et(i);
            }
        }
        st.add(sc);
    }
    
    void disp(){
        int prev = -1;
        boolean first = false;
        for(int i : st){
            if(prev == -1){
                prev = i;
                continue;
            }
            int small = prev;
            int big = i;
            if(i < prev){
                small = i;
                big = prev;
            }
            if(added[small] == big){
                prev = i;
                added[small]= 0;
                continue;
            }
            System.out.println(prev + " " + i);    
            prev = i;
        }
    }
    
    void solve(){
        at();
        System.out.println(res);
        for(int i = 1; i <= n ; ++i){
            if(visited[i] == 0){
                st.clear();
                et(i);
                disp();
            }
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
    private boolean isWhiteSpace(int n)
    {
        if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
        return true;
        return false;
    }
}


