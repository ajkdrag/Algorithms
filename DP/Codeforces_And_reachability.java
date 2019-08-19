/*
  Problem at : https://codeforces.com/contest/1168/problem/C
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        int n = reader.nextInt();
        int q = reader.nextInt();
        int[] arr = new int[n+1];
        for(int i = 1; i<= n; ++i)
            arr[i] = reader.nextInt();
        Solution sol = new Solution(n, arr);
        sol.computeNxt();
        sol.fillDp();
        while(q-- > 0){
            System.out.println(!sol.solve(reader.nextInt(), reader.nextInt()) ? "Fou" : "Shi");
        }
    }
}
 
class Solution {
    int[] arr;
    int[][] nxt;
    int[][] dp;
    int n;
    Set<Integer>[] al;
    
    Solution (int n, int[] arr){
        this.n = n;
        this.arr = arr;
        nxt = new int[n+1][20];
        dp = new int[n+1][20];
        al = new HashSet[n+1];
    }
    
    void computeNxt(){
        for(int i = n; i >= 1; --i){
            al[i] = new HashSet<Integer>();
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            for(int j = 0; j < 20; ++j){
                nxt[i][j] = (i == n ? -1 : nxt[i+1][j]);
                if(i < n && (arr[i+1] & (1<<j))>0)
                    nxt[i][j] = i+1;
                int val = nxt[i][j];
                if(val != -1 && (arr[i] & (1<<j))>0)
                    al[i].add(val);
                if((arr[i] & (1<<j))>0)
                    dp[i][j] = i;
            }
        }
    }
    
    void fillDp(){
        for(int i = n; i >= 1; --i){
            for(int j : al[i]){
                for(int k = 0; k < 20; ++k){
                        dp[i][k] = Math.min(dp[i][k], dp[j][k]);
                }
            }
        }
    }
    
    boolean solve(int a, int b){
        for(int i = 0; i < 20; ++i){
            int valA = dp[a][i];
            if(valA == Integer.MAX_VALUE)
                continue;
            if(valA == a && dp[b][i] == b)
                return true;
            if(valA != a && valA <= b && dp[b][i] == b)
                return true;
        }
        return false;
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
