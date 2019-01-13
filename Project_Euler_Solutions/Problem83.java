/*
  Problem at : https://projecteuler.net/problem=83
*/

import java.util.*;
import java.io.*;

public class Problem83 {
    public static void main(String args[]) throws IOException {
        int N = 80;        
        int[][] grid = new int[N][N];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        int row_num = -1;
        while((line = br.readLine()) != null){
            ++row_num;
            String[] row = line.split(",");
            for(int j = 0; j < N; ++j)
                grid[row_num][j] = Integer.parseInt(row[j]);
        }
        int[][] dp = new int[N][N];
        for(int i = 0; i < N; ++i){
            for(int j = 0; j < N; ++j){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = grid[0][0];
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(N*N, new Comparator<Integer>(){
            public int compare(Integer s1, Integer s2) { 
            if (grid[s1/N][s1%N] > grid[s2/N][s2%N]) 
                return 1; 
            else if (grid[s1/N][s1%N] < grid[s2/N][s2%N]) 
                    return -1; 
            return 1; 
            } 
        });    
        pQueue.add(0);
        while(!pQueue.isEmpty()){
            int curr = pQueue.poll();
            int curr_i = curr/N;
            int curr_j = curr%N;
            if(curr_i > 0){
                if(dp[curr_i][curr_j] + grid[curr_i - 1][curr_j] < dp[curr_i - 1][curr_j]){
                    dp[curr_i - 1][curr_j] = dp[curr_i][curr_j] + grid[curr_i - 1][curr_j];
                    pQueue.add(curr - N);
                }
            }
            if(curr_j > 0){
                if(dp[curr_i][curr_j] + grid[curr_i][curr_j-1] < dp[curr_i][curr_j-1]){
                    dp[curr_i][curr_j-1] = dp[curr_i][curr_j] + grid[curr_i][curr_j-1];
                    pQueue.add(curr - 1);
                }
            }
            if(curr_i < N - 1){
                if(dp[curr_i][curr_j] + grid[curr_i + 1][curr_j] < dp[curr_i + 1][curr_j]){
                    dp[curr_i + 1][curr_j] = dp[curr_i][curr_j] + grid[curr_i + 1][curr_j];
                    pQueue.add(curr + N);
                }
            }
            if(curr_j < N - 1){
                if(dp[curr_i][curr_j] + grid[curr_i][curr_j + 1] < dp[curr_i][curr_j + 1]){
                    dp[curr_i][curr_j + 1] = dp[curr_i][curr_j] + grid[curr_i][curr_j + 1];
                    pQueue.add(curr + 1);
                }
            }
        }
        System.out.println(dp[N-1][N-1]);
    }
}



