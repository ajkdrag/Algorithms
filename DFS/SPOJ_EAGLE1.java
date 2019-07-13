/*
  Problem at : https://www.spoj.com/problems/EAGLE1/
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String args[]) throws IOException{
        Scan sc = new Scan();
        Print pt = new Print();
        int t = sc.scanInt();
        while(t-- > 0){
            int N = sc.scanInt();
            Solution sol = new Solution(N);
            for(int i = 1; i < N; ++i)
                sol.addEdge(sc.scanInt(), sc.scanInt(), sc.scanInt());
            sol.solve();
            for(int i = 1; i <= N; ++i)
               pt.print(sol.dists[i] + " ");
            pt.println();
        }
        pt.close();
    }
}
 
class Solution {
    ArrayList<Node>[] adjList;
    long[] dists;
    int[] par;
    int N;
    
    Solution(int N){
        this.N = N;
        this.adjList = new ArrayList[N+1];
        for(int i = 1; i <= N; ++i)
            adjList[i] = new ArrayList<Node>();
        dists = new long[N+1];
        par = new int[N+1];
    }
    
    void addEdge(int a, int b, long wt){
        adjList[a].add(new Node(b, wt));
        adjList[b].add(new Node(a, wt));
    }
    
    void dfs(int parent, int start, long soFar){
        long curr = dists[start];
        if(soFar > curr)
            dists[start] = soFar;
        for(Node nbr : adjList[start]){
            if(nbr.id == parent)
                continue;
            dfs(start, nbr.id, soFar + nbr.wt);
        }
    }
    
    long dfs2(int parent, int start){
        if(par[start] != 0 && par[start] != parent)
            return dists[start];
        
        long currMax = 0;
        int best = 0;
        for(Node nbr : adjList[start]){
            if(nbr.id == parent)
                continue;
            long temp = nbr.wt + dfs2(start, nbr.id);
            if(temp > currMax){
                currMax = temp;
                best = nbr.id;
            }
        }
        
        if(currMax > dists[start]){
            dists[start] = currMax;
            par[start] = best;
        }
        return currMax;
    }
    
    void solve(){
        for(int i = 1; i <= N; ++i){
            if(adjList[i].size() > 1)
            dfs2(par[i], i);
        }
    }
}
 
class Node {
    int id;
    long wt;
    Node(int id, long wt){
        this.id = id;
        this.wt = wt;
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
    public void println()throws IOException
    {
        bw.append("\n");
    }
    public void close()throws IOException
    {
        bw.close();
    }
}

