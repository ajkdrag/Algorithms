/*
  Problem at : https://devskill.com/CodingProblems/ViewProblem/150
*/

import java.io.*;
import java.util.*;

 public class Ohani {
    public static void main(String args[]) throws IOException{
        Scan sc = new Scan();
        Print pt = new Print();
        int[] res = new int[2];
        int N = sc.scanInt();
        Solution sol = new Solution(N);
        for(int i = 2; i <= N; ++i){
            sol.addParent(i, sc.scanInt());
        }
        
        for(int i = 2; i <= N; ++i){
            sol.addWeight(i, sc.scanInt());
        }
        
        sol.init();
        int Q = sc.scanInt();
        while(Q-- > 0){
            int queryType = sc.scanInt();
            int nd = sc.scanInt();
            if(queryType == 2){
                pt.println(sol.root[nd] + " " + sol.rootWeight[nd]);
            }
            else
                sol.delete(nd);
        }
        pt.close();
    }
}

class Solution {
    int[] directParent;
    int[] weight;
    int[] root;
    long[] rootWeight;
    ArrayList<ArrayList<Integer>> childList;
    Queue<Integer> queue;
    int N;
    
    Solution(int N){
        this.N = N;
        directParent = new int[N+1];
        weight = new int[N+1];
        root = new int[N+1];
        rootWeight = new long[N+1];
        childList = new ArrayList<ArrayList<Integer>>(N+1);
        for(int i = 0; i<=N;++i){
            childList.add(new ArrayList<Integer>());
            directParent[i] = i;
        }
    }
    
    void addParent(int n, int parent){
        directParent[n] = parent;
        childList.get(parent).add(n);
    }
    
    void init(){
        for(int i = 1; i <= N; ++i){
            long[] res = findRoot(i);
            root[i] = (int) res[0];
            rootWeight[i] = res[1];
        }
    }
    
    void addWeight(int n, int w){
        weight[n] = w;
    }
    
    void delete(int n){
        if(directParent[n] == n)
            return;
        directParent[n] = n;
        weight[n] = 0;
        rootWeight[n] = 0;
        root[n] = n;
        // apply bfs
        queue = new LinkedList<Integer>();
        queue.add(n);
        
        while(!queue.isEmpty()){
            int curr = queue.poll();
            
            for(int nbr : childList.get(curr)){
                if(directParent[nbr] == nbr)
                    continue;
                root[nbr] = n;
                rootWeight[nbr] = rootWeight[curr] + weight[nbr];
                queue.offer(nbr);
            }
        }
    }
    
    long[] findRoot(int n){
        int par = directParent[n];
        long sum = weight[n];
        while(n != par){
            int r = root[par];
            if(r != 0){
              return new long[]{r, sum + rootWeight[par]};     
            }
            n = par;
            par = directParent[n];
            sum += weight[n];
        }
        return new long[]{par, sum};
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


