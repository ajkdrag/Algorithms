/*
  Problem at : https://codeforces.com/problemset/problem/580/C
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Scan sc = new Scan();
        Print pt = new Print();
        
        int N = sc.scanInt();
        int m = sc.scanInt();
        Solution sol = new Solution(N, m);
        for(int i = 1; i <= N; ++i){
            sol.adjList[i] = new ArrayList<Integer>();
            sol.cat[i] = sc.scanInt();
        }
        for(int i = 1; i < N; ++i){
            sol.addEdge(sc.scanInt(), sc.scanInt());
        }
        sol.dfs(0, 0, 1);
        pt.println(sol.res);
        pt.close();
    }
}

class Solution {
    int res = 0;
    ArrayList<Integer>[] adjList;
    int[] cat;
    int m;
    
    Solution(int N, int m){
        adjList = new ArrayList[N+1];
        this.m = m;
        cat = new int[N+1];
    }
    
    void addEdge(int a, int b){
        adjList[a].add(b);
        adjList[b].add(a);
    }
    
    void dfs(int soFar, int par, int start){
        if(cat[start] == 1)
            ++soFar;
        else
            soFar = 0;
            
        if(soFar > m)
            return;
            
        if(start != 1 && adjList[start].size() == 1){
            ++res;
            return;
        }
            
        for(int nbr : adjList[start]){
            if(nbr == par)
                continue;
            dfs(soFar, start, nbr);
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


