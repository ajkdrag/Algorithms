/*
  Problem at : https://codeforces.com/contest/859/problem/E
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        Reader reader = new Reader();
        int N = reader.nextInt();
        Solution sol = new Solution(N);
        for(int i = 1; i <= N; ++i){
            sol.union(reader.nextInt(), reader.nextInt());
        }
        System.out.println(sol.solve());
    }
}

class Solution {
    int N;
    Node[] dsu;
    static final int MOD = 1000000007;
    
    Solution(int N){
        this.N = N;
        dsu = new Node[(N<<1) + 1];
        for(int i = 1; i <= (N<<1); ++i){
            dsu[i] = new Node(i);
        }
    }
    
    long solve(){
        long res = 1;
        for(int i = 1; i <= (N<<1); ++i){
            res = (res*(dsu[i].cycleType == 0 ? dsu[i].size : dsu[i].cycleType))%MOD;
        }
        return res;
    }
    
    int find(int a){
        if(dsu[a].parent == a)
            return a;
        return dsu[a].parent = find(dsu[a].parent);
    }
    
    void union(int a, int b){
        int par_a = find(a);
        int par_b = find(b);
        if(par_a == par_b){
            if(a == b)
                dsu[par_a].cycleType = 1;
            else
                dsu[par_a].cycleType = 2;
            return;
        }
        if(dsu[par_a].size < dsu[par_b].size){
            int temp = par_a;
            par_a = par_b;
            par_b = temp;
        }
        dsu[par_b].parent = par_a;
        dsu[par_a].size += dsu[par_b].size;
        dsu[par_b].size = 1;
        dsu[par_a].cycleType += dsu[par_b].cycleType;
        dsu[par_b].cycleType = 0;
    }
}

class Node {
    int cycleType = 0;
    int size = 1;
    int parent = -1;
    
    Node(int i){
        this.parent = i;
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


