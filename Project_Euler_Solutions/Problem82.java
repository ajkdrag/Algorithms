/*
  Problem at : https://projecteuler.net/problem=82
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Problem81 {
    static int[]dp;
    static int[][] grid;
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        int row_num = 0;
        dp = new int[80];
        grid = new int[80][80];
        while((line = reader.readLine()) != null){
            String[] row = line.split(",");
            for(int col_num = 0; col_num < 80; ++col_num){
                grid[row_num][col_num] = Integer.parseInt(row[col_num]);                
            }
            ++row_num;
        }
        for (int i = 0; i < 80; i++) {
            dp[i] = grid[i][79];
        }
        for (int i = 78; i >= 0; i--) {
            dp[0] += grid[0][i];
            for (int j = 1; j < 80; j++) {
                dp[j] = Math.min(dp[j - 1] + grid[j][i], dp[j] + grid[j][i]);
            }
         
            for (int j = 78; j >= 0; j--) {
                dp[j] = Math.min(dp[j], dp[j+1] + grid[j][i]);
            }
        }
        
        int res = dp[0];
        for(int i = 1; i < 80; ++i){
            if(dp[i] < res)
                res = dp[i];
        }
        System.out.println(res);
    }
}


