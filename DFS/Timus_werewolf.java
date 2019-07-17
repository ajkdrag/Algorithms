/*
  Problem at : http://acm.timus.ru/problem.aspx?space=1&num=1242
*/

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Solution sol = new Solution(N);
        String line = "";
        while(!(line = br.readLine()).equals("BLOOD")){
            String[] ip = line.split(" ");
            sol.addEdge(Integer.parseInt(ip[0]), Integer.parseInt(ip[1]));
        }
        while((line = br.readLine()) != null){
            sol.victim(Integer.parseInt(line));
        }
        sol.solve();
    }
}

class Solution {
    ArrayList<Integer>[] adjList;
    ArrayList<Integer>[] parList;
    int[] visited;
    int N;
    
    Solution(int N){
        this.N = N;
        visited = new int[N+1];
        adjList = new ArrayList[N+1];
        parList = new ArrayList[N+1];
        for(int i = 1; i <= N; ++i){
            adjList[i] = new ArrayList<Integer>();
            parList[i] = new ArrayList<Integer>();
        }
    }
    
    void addEdge(int child, int par){
        adjList[par].add(child);
        parList[child].add(par);
    }
    
    void dfs(int start){
        visited[start] = 1;
        for(int child : adjList[start]){
            if(visited[child] == 0){
                dfs(child);
            }
        }
    }
    
    
    void dfs2(int start){
        visited[start] = 1;
        for(int par : parList[start]){
            if(visited[par] == 0){
                dfs2(par);
            }
        }
    }
    
    void victim(int i){
        dfs(i);
        dfs2(i);
    }
    
    void solve(){
        int temp = 0;
        for(int i = 1; i <= N; ++i){
            if(visited[i] == 0){
                temp = 1;
                System.out.print(i + " ");
            }   
        }
        
        if(temp < 1){
            System.out.println(0);
            return;
        }
    }
}
