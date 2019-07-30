/*
  Problem at : http://codeforces.com/contest/638/problem/C
*/

import java.io.*;
import java.util.*;

public class MyClass {
    public static void main(String args[]) throws IOException{
      Scan sc = new Scan();
      int n = sc.scanInt();
      Solution sol = new Solution(n);
      for(int i = 1; i < n; ++i){
          sol.addEdge(i, sc.scanInt(), sc.scanInt());
      }
      sol.solve();
    }
}

class Solution {
    ArrayList<Edge>[] mp;
    ArrayList<Integer>[] ans;
    int output;
    
    Solution(int n){
        mp = new ArrayList[n + 1];
        ans = new ArrayList[n + 1];
        for(int i = 1; i <= n; ++i){
            mp[i] = new ArrayList<Edge>();
            ans[i] = new ArrayList<Integer>();
        }
        output = -1;
    }
    
    void Dfs(int u,int from,int prenum)
    {
        int day=0;
        for(Edge e : mp[u])
        {
            int i = e.v;
            if(i==from)continue;
            day++;if(prenum==day)day++;
            ans[day].add(e.id);
            Dfs(i,u,day);
        }
        output=Math.max(output,day);
    }
    
    void addEdge(int i , int a, int b){
        mp[a].add(new Edge(b, i));
        mp[b].add(new Edge(a, i));
    }
    
    void solve() throws IOException{
        Print pt = new Print();
        Dfs(1, -1, 0);
        pt.println(output);
        for(int i=1;i<=output;i++)
        {
            pt.print(ans[i].size() + " ");
            for(int j : ans[i])
            {
                pt.print(j + " ");
            }
            pt.print("\n");
        }
        pt.close();
    }
    
}

class Edge {
    int v, id;
    Edge(int v, int id){
        this.v = v;
        this.id = id;
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
    public void println(Object object)throws IOException
    {
        print(object);
        bw.append("\n");
    }
    public void close()throws IOException
    {
        bw.close();
    }
}

