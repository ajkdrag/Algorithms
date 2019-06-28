/*
  Problem at : https://onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&page=show_problem&problem=1909
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Scan sc = new Scan();
        Print pt = new Print();
        
        int n = sc.scanInt();
        int m = sc.scanInt();
        while(n + m != 0){
            Solution sol = new Solution(n);
            while(m-- > 0){
                sol.addEdge(sc.scanInt(), sc.scanInt());
            }
            int res = sol.bfs();
            if(res == -1)
                pt.println("Poor Koorosh");
            else
                pt.println(res);
            n = sc.scanInt();
            m = sc.scanInt();
        }
        pt.close();
    }
}

class Solution {
    int[] degree;
    ArrayList<Integer>[] adjList;
    Queue<Integer> q;
    int[] visited;
    int N;
    
    Solution(int N){
        this.N = N;
        adjList = new ArrayList[N+1];
        degree = new int[N+1];
        visited = new int[N+1];
    }
    
    void addEdge(int a, int b){
        degree[a]++;
        degree[b]++;
        if(adjList[a] == null)
            adjList[a] = new ArrayList<Integer>();
        if(adjList[b] == null)
            adjList[b] = new ArrayList<Integer>();
        adjList[a].add(b);
        adjList[b].add(a);
    }
    
    int bfs(){
        int[] oddNodes = new int[2];
        int id = 0;
    	for (int i = 1; i <= N; i++) {
    		if (degree[i] == 1 || degree[i] == 0) return -1;
    		if ((degree[i] & 1) == 1)
    			oddNodes[id++] = i;
    	}
    	if (id == 0) {
    		return 0;
    	}
    	
    	q = new LinkedList<Integer>();
    	q.offer(oddNodes[0]);
    	while (!q.isEmpty()) {
    		int u = q.poll();
    		if (u == oddNodes[1])
    			return visited[u];
    		for (int nbr : adjList[u]) {
    			if (degree[nbr] == 2) continue;
    			if (visited[nbr] > 0) continue;
    			visited[nbr] = visited[u] + 1;
    			q.offer(nbr);
    		}
    	}
    	return -1;
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


