/*
  Problem at : https://www.spoj.com/problems/WATER
*/

// using priority queue (accepted)
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException{
        Scan sc = new Scan();
        Print pt = new Print();
        
        int t = sc.scanInt();
        while(t-- > 0){
            int r = sc.scanInt();
            int c = sc.scanInt();
            Solution sol = new Solution(r, c);
            for(int i = 0; i < r; ++i){
                for(int j = 0; j < c; ++j)
                    sol.addCell(i, j, sc.scanInt());
            }
            pt.println(sol.trapRainWater());
        }
        pt.close();
    }
}

class Solution {
    int m;
    int n;
    int[][] heightMap;
    
    Solution(int m, int n){
        this.m = m;
        this.n = n;
        this.heightMap = new int[m][n];
    }
    
    void addCell(int r, int c, int val){
        this.heightMap[r][c] = val;
    }
    
    public int trapRainWater() {
        if (m == 0) {
            return 0;
        }
        if (n == 0) {
            return 0;
        }
        PriorityQueue<Cell> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            pq.add(new Cell(i, 0, heightMap[i][0]));
            visited[i][0] = true;
            pq.add(new Cell(i, n - 1, heightMap[i][n - 1]));
            visited[i][n - 1] = true;
        }
         
        for (int j = 1; j < n - 1; j++) {
            pq.add(new Cell(0, j, heightMap[0][j]));
            visited[0][j] = true;
            pq.add(new Cell(m - 1, j, heightMap[m - 1][j]));
            visited[m - 1][j] = true;
        }
         
        int[] dirX = {0, 0, -1, 1};
        int[] dirY = {-1, 1, 0, 0};
        int sum = 0;
        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dirX[i];
                int ny = curr.y + dirY[i];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !visited[nx][ny]) {
                    sum += Math.max(0, curr.h - heightMap[nx][ny]);
                    pq.add(new Cell(nx, ny, Math.max(heightMap[nx][ny], curr.h)));
                    visited[nx][ny] = true;
                }
            }
        }
        return sum;
    }
     
    private class Cell implements Comparable<Cell> {
        int x, y, h;
        public Cell(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
         
        public int compareTo(Cell other) {
            return h - other.h;
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


