/*
  Problem at : http://codeforces.com/contest/29/problem/D
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Scan sc = new Scan();
        Print pt = new Print();
        int N = sc.scanInt();
        Solution sol = new Solution(N);
        for(int i = 1; i < N; ++i){
            sol.addEdge(sc.scanInt(), sc.scanInt());
        }
        sol.dfs2(1, 0, new ArrayList<Integer>(400));
        for(int i = 1; i <= sol.k; ++i)
            sol.addOrder(sc.scanInt());
        sol.dfs(1, 0);
        if(!sol.queue.isEmpty())
            pt.println(-1);
        else
            pt.println(sol.sb);
        pt.close();
    }
}

class Solution {
    ArrayList<Integer>[] adjList;
    Queue<Integer> queue;
    int[] visited;
    int[][] hasLeaf;
    int N, k;
    StringBuilder sb;
    
    void dfs(int src, int par){
        sb.append(src);
        sb.append(' ');
        if(src == queue.peek()){
            queue.poll();
            visited[src] = 1;
            return;
        }
        for(int i = 0, j = adjList[src].size(); i < j; ++i){
            int nb = adjList[src].get(i);
            if(nb == par || visited[nb] == 1)
                continue;
            if(hasLeaf[nb][queue.peek()] == 1){
                dfs(nb, src);
                sb.append(src);
                sb.append(' ');
                i = -1;
            }
        }
        visited[src] = 1;
    }
    
    void dfs2(int src, int par, ArrayList<Integer> seen){
        if(par != 0 && adjList[src].size() == 1){
            ++k;
            hasLeaf[src][src] = 1;
            for(int i : seen){
                hasLeaf[i][src] = 1;
            }
            return;
        }
        seen.add(src);
        for(int nb : adjList[src]){
            if(nb == par)
                continue;
            dfs2(nb, src, seen);
        }
        seen.remove(seen.size()-1);
    }
    
    void addEdge(int a, int b){
        adjList[a].add(b);
        adjList[b].add(a);
    }
    
    void addOrder(int el){
        queue.offer(el);
    }
    
    Solution(int N){
        this.N = N;
        queue = new LinkedList<Integer>();
        visited = new int[N+1];
        hasLeaf = new int[N+1][N+1];
        adjList = new ArrayList[N+1];
        for(int i = 1; i <= N; ++i){
            adjList[i] = new ArrayList<Integer>();
        }
        sb = new StringBuilder();
    }
    
}

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


