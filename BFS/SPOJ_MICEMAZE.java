/*
  Problem at : https://www.spoj.com/problems/MICEMAZE/
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Scan sc = new Scan();
        Print pt = new Print();
        
        int N = sc.scanInt();
        int E = sc.scanInt();
        int T = sc.scanInt();
        Solution sol = new Solution(N, E, T);
        int M = sc.scanInt();
        while(M-- > 0){
            int a = sc.scanInt();
            int b = sc.scanInt();
            int w = sc.scanInt();
            sol.addEdge(a, b, w);
        }
        pt.println(sol.bfs());
        pt.close();
    }
}

class Solution {
    int[] visited;
    int[] timeLeft;
    ArrayList<Node>[] adjList;
    Queue<Integer> queue;
    int exitCell;
    int timer;
    int nodes;
    
    Solution(int N, int E, int T){
        nodes = N;
        exitCell = E;
        timer = T;
        visited = new int[N+1];
        timeLeft = new int[N+1];
        adjList = new ArrayList[N+1];
    }
    
    void addEdge(int a, int b, int w){
        if(adjList[b] == null)
            adjList[b] = new ArrayList<Node>();
        adjList[b].add(new Node(a, w));
    }
    
    int bfs(){
        if(nodes < 2)
            return nodes;
        int sum = 0;
        queue = new LinkedList<Integer>();
        queue.add(exitCell);
        timeLeft[exitCell] = timer;
        while(!queue.isEmpty()){
            int curr = queue.poll();
            if(visited[curr] == 0){
                visited[curr] = 1;
                ++sum;
            }
            if(adjList[curr] != null){
                for(Node nbr : adjList[curr]){
                    int t = timeLeft[curr] - nbr.edgeWeight;
                    if(t >= 0 && nodes != sum){
                        timeLeft[nbr.nodeVal] = Math.max(t, timeLeft[nbr.nodeVal]);
                        queue.offer(nbr.nodeVal);
                    }
                }
            }
        }
        return sum;
    }
}

class Node {
    int nodeVal;
    int edgeWeight;
    
    Node(int nodeVal, int edgeWeight){
        this.nodeVal = nodeVal;
        this.edgeWeight = edgeWeight;
    }
    
    public String toString(){
        return nodeVal + " (" + edgeWeight + ") ";
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


