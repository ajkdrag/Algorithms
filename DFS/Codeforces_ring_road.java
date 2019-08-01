/*
  Problem at : https://codeforces.com/contest/24/problem/A
*/

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) throws IOException{
        Scan sc = new Scan();
        int N = sc.scanInt();
        Solution sol = new Solution(N);
        for(int i = 1; i <= N; ++i){
            sol.addEdge(sc.scanInt(), sc.scanInt(), sc.scanInt());
        }
        System.out.println(sol.solve());
    }
}

class Solution {
    ArrayList<Edge>[] adjList;
    int total;
    int incurred;
    void dfs(int curr, int parent,int soFar){
        if(parent != 0 && curr == 1){
            incurred = soFar;
            return;
        }
        for(Edge nbr : adjList[curr]){
            if(nbr.to == parent)
                continue;
            dfs(nbr.to, curr, soFar + nbr.cost);
            break;
        }        
    }
    
    int solve(){
        dfs(1, 0, 0);
        return incurred < total-incurred ? incurred : total-incurred;
    }
    
    void addEdge(int a, int b, int cost){
        adjList[a].add(new Edge(b, 0));
        adjList[b].add(new Edge(a, cost));
        total += cost;
    }
    
    Solution(int N){
        adjList = new ArrayList[N+1];
        for(int i = 1; i <= N; ++i)
            adjList[i] = new ArrayList<Edge>(2);
    }   
    
}

class Edge {
    int to;
    int cost;
    Edge(int to, int cost){
        this.to = to;
        this.cost = cost;
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


