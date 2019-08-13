/*
  Problem at : https://codeforces.com/contest/920/problem/E
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        int n = reader.nextInt();
        int m = reader.nextInt();
        Sol sol = new Sol(n);
        while(m-- > 0){
            sol.addForb(reader.nextInt(), reader.nextInt());
        }
        sol.solve();
    }
}

class Sol {
    int[] next, prev;
    int N, cnt;
    HashSet<Integer>[] forb;
    List<Integer>temp, res;
    
    Sol(int N){
        this.N = N;
        forb = new HashSet[N+1];
        next = new int[N+2];
        prev = new int[N+2];
        for(int i = 0; i <= N; ++i){
            forb[i] = new HashSet<Integer>();
            next[i] = i+1;
            if(i > 0)
                prev[i] = i-1;
        }
        prev[N+1] = N;
    }
    
    void addForb(int a, int b){
        forb[a].add(b);
        forb[b].add(a);
    }
    
    void delete(int x){
        int pr = prev[x];
        next[pr] = next[x];
        prev[next[x]] = pr;
        next[x] = next[pr];
    }
    
    void dfs(int st){
        int start = next[0];
        int end = N+1;
        temp = new ArrayList<Integer>();
        while(start < end){
            if(!forb[st].contains(start)){
                temp.add(start);
                cnt++;
                delete(start);
            }
            start = next[start];
        }
        for(int nbr : temp)
            dfs(nbr);
    }
    
    void solve(){
        int start = 1;
        int end = N+1;
        int ct = 0;
        res = new ArrayList<Integer>();
        while(start != end){
            ++ct;
            cnt = 1;
            delete(start);
            dfs(start);
            res.add(cnt);
            start = next[0];            

        }
        System.out.println(ct);
        Collections.sort(res);
        for(int el : res)
            System.out.print(el + " ");
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


