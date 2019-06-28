/*
  Problem at : https://codeforces.com/contest/796/problem/D
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        Scan sc = new Scan();
        Print pt = new Print();
        
        int N = sc.scanInt();
        int K = sc.scanInt();
        int D = sc.scanInt();
        
        Solution sol = new Solution(N, K, D);
        while(K-- > 0)
            sol.addPolice(sc.scanInt());
        for(int i = 1; i < N; ++i){
            sol.addEdge(i, sc.scanInt(), sc.scanInt());
        }
        pt.println(sol.bfs());
        for(int i = 1; i < N; ++i){
            if(sol.res[i] == 0)
                pt.print(i + " ");
        }
        pt.close();
    }
}

class Solution {
    Queue<Integer> q;
    int[] visited;
    ArrayList<Edge>[] adjList;
    int[] res;
    int D, N;
        
    Solution(int N, int K, int D){
        this.D = D;
        this.N = N;
        q = new LinkedList<Integer>();
        visited = new int[N+1];
        adjList = new ArrayList[N+1];
        res = new int[N];
    }
    
    void addEdge(int id, int a, int b){
        if(adjList[a] == null)
            adjList[a] = new ArrayList<Edge>();
        if(adjList[b] == null)
            adjList[b] = new ArrayList<Edge>();
        adjList[a].add(new Edge(id, b));
        adjList[b].add(new Edge(id, a));
    }
    
    void addPolice(int p){
        if(visited[p] == 0){
            visited[p] = p;
            q.add(p);
        }
    }
    
    int bfs(){
        int sz = q.size();
        int dist = 0;
        int validCt = 0;
        while(!q.isEmpty()){
            if(D == dist)
                break;
            int curr = q.poll();
            --sz;
            for(Edge nbr : adjList[curr]){
                if(nbr.to == curr)
                    continue;
                if(visited[nbr.to] != 0)
                    continue;
                visited[nbr.to] = visited[curr];
                res[nbr.id] = 1;
                ++validCt;
                q.offer(nbr.to);
            }
            if(sz == 0){
                sz = q.size();
                ++dist;
            }
        }
        return N-1-validCt;
    }
}

class Edge {
    int id;
    int to;
    Edge(int id, int to){
        this.id = id;
        this.to = to;
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


