/*
  Problem at : https://codeforces.com/contest/839/problem/C 
*/

import java.io.*;
import java.util.*;
 
public class Main{
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        int n = reader.nextInt();
        Solution sol = new Solution(n);
        while(n-- > 1){
            sol.ae(reader.nextInt(), reader.nextInt());
        }
        sol.solve();
    }
}
 
class Solution {
    List<Integer>[] al;
    int[] vis;
    int N;
    double res, ct;
    
    Solution (int N){
        res = 0;
        ct = 0;
        this.N = N;
        vis = new int[N+1];
        al = new ArrayList[N+1];
        for(int i = 1; i <= N; ++i)
            al[i] = new ArrayList<Integer>();
    }
    
    void ae(int a, int b){
        al[a].add(b);
        al[b].add(a);
    }
    
    void run(int st, int par, int len, double prob){
        if(par != -1 && al[st].size() == 1){
            res +=(prob*len);
            return;
        }
        // if(vis[st] == 1)
        //     return;
        // vis[st] = 1;
        double nextProb = 1.0/(al[st].size()-(par == -1 ? 0 : 1));
        for(int nbr : al[st]){
            if(nbr == par)
                continue;
            run(nbr, st, len + 1, prob*nextProb);
        }
    }
    
    void solve(){
        run(1, -1, 0, 1);
        System.out.printf("%f",res);
    }
}
 
class Reader { 
    final private int BUFFER_SIZE = 1 << 16; 
    private DataInputStream din; 
    private byte[] buffer; 
    private int bufferPointer, bytesRead; 
 
    public Reader() 
    { 
        din = new DataInputStream(System.in); 
        buffer = new byte[BUFFER_SIZE]; 
        bufferPointer = bytesRead = 0; 
    } 
 
    public Reader(String file_name) throws IOException 
    { 
        din = new DataInputStream(new FileInputStream(file_name)); 
        buffer = new byte[BUFFER_SIZE]; 
        bufferPointer = bytesRead = 0; 
    } 
 
    public String readLine() throws IOException 
    { 
        byte[] buf = new byte[64]; // line length 
        int cnt = 0, c; 
        while ((c = read()) != -1) 
        { 
            if (c == '\n') 
                break; 
            buf[cnt++] = (byte) c; 
        } 
        return new String(buf, 0, cnt); 
    } 
 
    public int nextInt() throws IOException 
    { 
        int ret = 0; 
        byte c = read(); 
        while (c <= ' ') 
            c = read(); 
        boolean neg = (c == '-'); 
        if (neg) 
            c = read(); 
        do
        { 
            ret = ret * 10 + c - '0'; 
        }  while ((c = read()) >= '0' && c <= '9'); 
 
        if (neg) 
            return -ret; 
        return ret; 
    } 
 
    public long nextLong() throws IOException 
    { 
        long ret = 0; 
        byte c = read(); 
        while (c <= ' ') 
            c = read(); 
        boolean neg = (c == '-'); 
        if (neg) 
            c = read(); 
        do { 
            ret = ret * 10 + c - '0'; 
        } 
        while ((c = read()) >= '0' && c <= '9'); 
        if (neg) 
            return -ret; 
        return ret; 
    } 
 
    public double nextDouble() throws IOException 
    { 
        double ret = 0, div = 1; 
        byte c = read(); 
        while (c <= ' ') 
            c = read(); 
        boolean neg = (c == '-'); 
        if (neg) 
            c = read(); 
 
        do { 
            ret = ret * 10 + c - '0'; 
        } 
        while ((c = read()) >= '0' && c <= '9'); 
 
        if (c == '.') 
        { 
            while ((c = read()) >= '0' && c <= '9') 
            { 
                ret += (c - '0') / (div *= 10); 
            } 
        } 
 
        if (neg) 
            return -ret; 
        return ret; 
    } 
 
    private void fillBuffer() throws IOException 
    { 
        bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE); 
        if (bytesRead == -1) 
            buffer[0] = -1; 
    } 
 
    private byte read() throws IOException 
    { 
        if (bufferPointer == bytesRead) 
            fillBuffer(); 
        return buffer[bufferPointer++]; 
    } 
 
    public void close() throws IOException 
    { 
        if (din == null) 
            return; 
        din.close(); 
    }
} 

