/*
  Problem at : https://codeforces.com/contest/909/problem/E
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        int n = sc.nextInt();
        int m = sc.nextInt();
        Sol sol = new Sol(n);
        for(int i = 0; i < n; ++i)
            sol.addProc(i, sc.nextInt());
        while(m-- > 0){
            sol.ae(sc.nextInt(), sc.nextInt()); 
        }
        sol.solve();
        System.out.println(sol.res);
    }
}
 
class Sol {
    int res;
    int N;
    int[] proc;
    int[] anc;
    int[] v;
    List<Integer>[] al;
    
    Sol(int N){
        res = 0;
        this.N = N;
        anc = new int[N+1];
        proc = new int[N+1];
        v = new int[N+1];
        al = new ArrayList[N+1];
        for(int i = 0; i <= N; ++i){
            al[i] = new ArrayList<Integer>();
        }
    }
    
    int run(int sr){
        if(v[sr] > 0)
            return v[sr];
        int max = proc[sr];
        for(int nbr : al[sr]){
            int temp = run(nbr);
            temp += ((proc[sr] == 1 && proc[nbr] == 0) ? 1 : 0);
            max = temp > max ? temp : max;
        }
        v[sr] = max;
        return max;
    }
    
    void solve(){
        for(int i = 0; i < N; ++i){
            if(anc[i] == 0){
                al[N].add(i);
            }
        }
        res = run(N);
    }
    
    void ae(int a, int b){
        al[b].add(a);   
        anc[a]++;
    }
    
    void addProc(int a, int b){
        proc[a] = b;
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

