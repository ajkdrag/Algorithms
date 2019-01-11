/*
  Problem at : https://projecteuler.net/problem=81
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Problem81 {
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line = "";
        int row_num = 0;
        dp = new int[80][80];
        while((line = reader.readLine()) != null){
            String[] row = line.split(",");
            for(int col_num = 0; col_num < 80; ++col_num){
                dp[row_num][col_num] = Integer.parseInt(row[col_num]);
                if(row_num != 0 && col_num == 0)
                    dp[row_num][col_num] += dp[row_num - 1][col_num];
                else if(row_num == 0 && col_num != 0)
                    dp[row_num][col_num] += dp[row_num][col_num - 1];
                else if(row_num != 0 && col_num != 0)
                    dp[row_num][col_num] += Math.min(dp[row_num - 1][col_num], dp[row_num][col_num - 1]);
            }
            ++row_num;
        }
        System.out.println(dp[79][79]);
    }
}


