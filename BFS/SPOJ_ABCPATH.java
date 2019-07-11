/*
  Problem at : https://www.spoj.com/problems/ABCPATH/
*/

// using bfs
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[])throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ip = "";
        int s= 1;
        while(!(ip = br.readLine()).equals("0 0")){
            String[] ips = ip.split(" ");
            Solution sol = new Solution(Integer.parseInt(ips[0]), Integer.parseInt(ips[1]));
            for(int i = 0; i < sol.W; ++i)
                sol.addRow(i, br.readLine());
            sol.solve();
            System.out.println("Case " + s++ + ": " + sol.max);
        }
    }
}


class Solution {
    int[][] dists;
    char[][] grid;
    ArrayList<Integer> a_list = new ArrayList<Integer>(2048);
    Queue<Integer> q;
    int max;
    int H, W;
    
    int[] dirX = {1, 1, 1, 0, 0, -1, -1, -1};
    int[] dirY = {-1, 0, 1, -1, 1, -1, 0, 1};
    
    Solution(int W, int H){
        this.H = H;
        this.W = W;
        this.grid = new char[W][H];
        this.dists = new int[W][H];
    }
    
    void addRow(int r, String s){
    	int j = 0;
        for(char c : s.toCharArray()){
        	grid[r][j] = c;
            if(c == 'A'){
                a_list.add(r*H + j);
                dists[r][j] = 1;
            }
            ++j;
        }
    }
    
    void bfs(int start){
        q = new LinkedList<Integer>();
        q.offer(start);
        while(!q.isEmpty()){
            int curr = q.poll();
            int currX = curr/H;
            int currY = curr - (currX*H);
            for(int i = 0; i < 8; ++i){
                int newX = currX + dirX[i];
                int newY = currY + dirY[i];
                int newD = dists[currX][currY] + 1;
                if(newX < 0 || newY < 0 || newX >= W || newY >= H || grid[newX][newY] - grid[currX][currY] != 1 || dists[newX][newY] >= newD)
                    continue;
                
                dists[newX][newY] = newD;
                q.offer(newX*H + newY);
                max = newD > max ? newD : max;
            }            
        }
    }
    
    void solve(){
        max = a_list.isEmpty() ? 0 : 1;
        for(int i : a_list){
            bfs(i);
        }
    }
}

// using dfs
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[])throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String ip = "";
        int s = 1;
        while(!(ip = br.readLine()).equals("0 0")){
            String[] ips = ip.split(" ");
            Solution sol = new Solution(Integer.parseInt(ips[0]), Integer.parseInt(ips[1]));
            for(int i = 0; i < sol.W; ++i)
                sol.addRow(i, br.readLine());
            sol.solve();
            System.out.println("Case " + s++ + ": " + sol.max);
        }
    }
}


class Solution {
    boolean[][] visited;
    char[][] grid;
    ArrayList<Integer> a_list = new ArrayList<Integer>(2048);
    int max = 0;
    int H, W;
    
    int[] dirX = {1, 1, 1, 0, 0, -1, -1, -1};
    int[] dirY = {-1, 0, 1, -1, 1, -1, 0, 1};
    
    Solution(int W, int H){
        this.H = H;
        this.W = W;
        this.grid = new char[W][H];
        this.visited = new boolean[W][H];
    }
    
    void addRow(int r, String s){
    	int j = 0;
        for(char c : s.toCharArray()){
        	grid[r][j] = c;
            if(c == 'A')
                a_list.add(r*H + j);
            ++j;
        }
    }
    
    void dfs(int currX, int currY, int len){
    	max = len > max ? len : max;
    	visited[currX][currY] = true;
        for(int i = 0; i < 8; ++i){
            int newX = currX + dirX[i];
            int newY = currY + dirY[i];
            if(newX < 0 || newY < 0 || newX >= W || newY >= H || grid[newX][newY] - grid[currX][currY] != 1 || visited[newX][newY])
                continue;
            dfs(newX, newY, len+1);            
       }      
    }
    
    void solve(){
        for(int i : a_list){
            int x = i/H;
            int y = i - (x*H);
            dfs(x,y,1);
        }
    }
}


