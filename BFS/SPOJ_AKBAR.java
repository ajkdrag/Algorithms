/*
  Problem at : https://www.spoj.com/problems/AKBAR
*/

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Scan in = new Scan();
        Print out = new Print();
        Solution sol = null;
        long test = in.scanInt();
        while(test-- > 0){
            boolean optimized = true;
            sol = new Solution(in.scanInt());
            int R = in.scanInt();
            int M = in.scanInt();
            while(R-- > 0){
                sol.addEdge(in.scanInt(), in.scanInt());
            }
            while(M-- > 0){
                if(!sol.markBfs(M+1, in.scanInt(), in.scanInt())){
                    optimized = false;
                    break;
                }
            }
            if(optimized && sol.numVisited == sol.N)
                out.println("Yes");
            else{
                out.println("No");
                while(M-- > 0){
                    in.scanInt();
                    in.scanInt();   
                }
            }
        }
        out.close();
    }
}

class Solution {
    
    ArrayList<Integer>[] adjList;
    int[] visited;
    int numVisited;
    int N;
    Queue<Integer> queue;
    
    Solution(int N){
        this.N = N;
        adjList = new ArrayList[N+1];
        visited = new int[N+1];
        numVisited = 0;
    }
    
    void addEdge(int a, int b){
        if(a == b)
            return;
        if(adjList[a] == null)
            adjList[a] = new ArrayList<Integer>();
        if(adjList[b] == null)
            adjList[b] = new ArrayList<Integer>();
        adjList[a].add(b);
        adjList[b].add(a);
    }
    
    boolean markBfs(int marker, int currNode, int strength){
        queue = new LinkedList<Integer>();
        queue.add(currNode);
        if(visited[currNode] != 0)
            return false;
        visited[currNode] = marker;
        ++numVisited;
        int size = 1;
        while(!queue.isEmpty()){
            --size;
            int nd = queue.poll();
            if(strength == 0)
                break;
            for(int nbr : adjList[nd]){
                if(visited[nbr] == 0){
                    visited[nbr] = marker;
                    ++numVisited;
                    queue.offer(nbr);
                    continue;
                }
                else if(visited[nbr] != marker)
                    return false;
                else
                    continue;
            }
            if(size == 0){
                strength--;
                size = queue.size();
            }
        }
        return true;
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


