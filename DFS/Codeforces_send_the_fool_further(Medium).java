/*
  Problem at : https://codeforces.com/contest/802/problem/K
*/

import java.io.*;
import java.util.*;
 
public class Main {
    public static void main(String[] args) throws IOException{
        Reader sc = new Reader();
        int n = sc.nextInt();
        int K = sc.nextInt();
        Sol sol = new Sol(n, K);
        for(int i = 1; i < n; ++i){
            sol.ae(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        sc.close();
        sol.solve(0, -1);
        System.out.println(sol.arrIn[0]);
    }   
}
 
class Sol {
    int[] arrIn, arrOut;
    List<Eg>[] al;
    int n, K;
    
    Sol(int n, int K){
        this.n = n;
        this.K = K;
        arrIn = new int[n+1];
        arrOut = new int[n+1];
        al = new ArrayList[n+1];
        for(int i = 0; i <= n; ++i){
            al[i] = new ArrayList<Eg>();
        }
    }
    
    void solve(int st, int par){
        if(al[st].size() == 1 && par != -1)
            return;
        int sz = al[st].size();
        if(par != -1)
            --sz;
        Eg[] temp = new Eg[sz];
        int ct = 0;
        for(Eg nbr : al[st]){
            if(nbr.to == par)
                continue;
            solve(nbr.to, st);
            temp[ct++] = nbr;
        }
        Arrays.sort(temp, new Comparator<Eg>(){
            public int compare(Eg a, Eg b){
                if(arrOut[a.to] + a.cst < arrOut[b.to] + b.cst)
                    return 1;
                else if(arrOut[a.to] + a.cst > arrOut[b.to] + b.cst)
                    return -1;
                else
                    return 0;
            }
        });
        
        int sum = 0;
        int min = Integer.MAX_VALUE;
        int minIn = Integer.MAX_VALUE;
        for(int i = 0, j = Math.min(K-1, sz-1); i <= j; ++i){
            min = Math.min(min, arrOut[temp[i].to] - arrIn[temp[i].to]);
            sum += arrOut[temp[i].to] + temp[i].cst;
        }
        if(sz > K-1)
            arrOut[st] = sum - (arrOut[temp[K-1].to] + temp[K-1].cst);
        else
            arrOut[st] = sum;
        minIn = sum - min;
        sum = arrOut[st];
        int max = -1;
        for(int i = K-1, j = ct-1; i <= j; ++i){
            max = Math.max(max, arrIn[temp[i].to]+temp[i].cst);
        }
        minIn = Math.max(minIn, (sum + max));
        arrIn[st] = minIn;
        
    }
    
    void ae(int a, int b, int c){
        al[a].add(new Eg(b, c));
        al[b].add(new Eg(a, c));
    }
}
 
class Eg {
    int to;
    int cst;
    Eg(int to, int cst){
        this.to = to;
        this.cst = cst;
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

