/*
  Problem at : https://www.spoj.com/problems/DIGOKEYS/
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Scan sc = new Scan();
        Print pt = new Print();
        Solution sol = null;
        int t = sc.scanInt();
        while(t-- > 0){
            int N = sc.scanInt();
            sol = new Solution(N);
            for(int i = 1; i < N; ++i){
                int numAdj = sc.scanInt();
                int[] temp = new int[numAdj];
                for(int j = 0; j < numAdj; ++j)
                    temp[j] = sc.scanInt();
                Arrays.sort(temp);
                for(int j : temp)
                    sol.addEdge(i, j);
            }
            pt.println(sol.bfs());
        }
        pt.close();
    }
}

class Solution {
    int[] opened;
    Queue<Node> q;
    ArrayList<Integer>[] adjList;
    int N;
    
    Solution(int N){
        this.N = N;
        opened = new int[N];
        adjList = new ArrayList[N];
    }
    
    void addEdge(int a, int b){
        if(adjList[a] == null)
            adjList[a] = new ArrayList<Integer>();
        adjList[a].add(b);
    }
    
    String bfs(){
        q = new LinkedList<Node>();
        q.add(new Node(1, 0));
        
        while(!q.isEmpty()){
            Node curr = q.poll();
            if(adjList[curr.id] != null){
                for(int nbr : adjList[curr.id]){
                    if(nbr == N)
                        return (curr.cnt+1) + "\n" + curr.history.append(curr.id).toString() ;
                    if(opened[nbr] == 0){
                        opened[nbr] = 1;
                        q.offer(new Node(nbr, curr.history.append(curr.id), curr.cnt+1));
                    }
                }
            }
        }
        
        return "-1";
    }
}

class Node {
    StringBuilder history = new StringBuilder();
    int id;
    int cnt;
    
    Node(int id, int cnt){
        this.id = id;
        this.cnt = cnt;
    }
    
    Node(int id, StringBuilder hist, int cnt){
        this.id = id;
        this.history = hist;
        this.history.append(" ");
        this.cnt = cnt;
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


